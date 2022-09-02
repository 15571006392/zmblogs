package com.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Zm-Mmm
 */
@Service
public class SignService {

    private final RedisTemplate redisTemplate;

    @Autowired
    public SignService(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 判断用户当日是否签到
     * @param userId 用户id
     * @param dateStr 日期
     * @return 是否签到
     */
    public boolean currentDaySign(int userId,String dateStr){
        // 获得日期
        Date date = getDate(dateStr);
        // 获取日期对应的天数
        int day = DateUtil.dayOfMonth(date) - 1;
        // 创建key
        String signKey = buildSignKey(userId, date);
        // 查看指定日期是否已经签到
        boolean isSigned = redisTemplate.opsForValue().getBit(signKey, day);
        return isSigned;
    }


    /**
     * @param userId  用户id
     * @param dateStr 日期
     * @return 签到结果
     */
    public Map<String, Object> doSign(int userId, String dateStr) {
        Map<String, Object> result = new HashMap<>(2);
        // 获得日期
        Date date = getDate(dateStr);
        // 获取日期对应的天数
        int day = DateUtil.dayOfMonth(date) - 1;
        // 创建key
        String signKey = buildSignKey(userId, date);
        // 查看指定日期是否已经签到
        boolean isSigned = currentDaySign(userId, dateStr);
        if (isSigned) {
            result.put("message", "今天已经签到了");
            result.put("code", 400);
            return result;
        }
        // 签到
        redisTemplate.opsForValue().setBit(signKey, day, true);
        // 计入总签到次数
        allSignCountByUser(userId);
        // 计入连续签到记录
        continuousSignHistory(userId);
        result.put("message", "签到成功");
        result.put("code", 200);
        return result;
    }

    /**
     * 统计总签到次数
     * @param userId 用户id
     */
    public void allSignCountByUser(int userId) {
        // 获取总签到次数
        String key = "allSignCountByUser";
        long allSignCountByUser = 0;
        if (redisTemplate.opsForHash().get(key, String.valueOf(userId)) != null) {
            allSignCountByUser = Long.valueOf((String) redisTemplate.opsForHash().get(key, String.valueOf(userId)));
        }
        allSignCountByUser += 1;
        /*
        放入redis
        map的key是allSignCountByUser
        value的key是用户id
        用户id的value为签到总次数
         */
        redisTemplate.opsForHash().put(key, String.valueOf(userId), String.valueOf(allSignCountByUser));
    }

    /**
     * 统计连续签到记录
     * @param userId 用户id
     */
    public void continuousSignHistory(int userId) {
        // 连续签到记录
        String key = "continuousSignHistoryCount";
        long continuousSignHistoryCount = 0;
        if (redisTemplate.opsForHash().get(key, String.valueOf(userId)) != null) {
            continuousSignHistoryCount = Long.valueOf((String) redisTemplate.opsForHash().get(key, String.valueOf(userId)));
        }
        continuousSignHistoryCount += 1;
        /*
        放入redis
        map的key是continuousSignHistoryCount
        value的key是用户id
        用户id的value为连续签到记录
         */
        redisTemplate.opsForHash().put(key, String.valueOf(userId), String.valueOf(continuousSignHistoryCount));
    }

    /**
     * @param userId  用户id
     * @param dateStr 日期
     * @return 当天签到情况和月签到情况
     */
    public Map<String, Object> getSignByDate(int userId, String dateStr) {
        Map<String, Object> result = new HashMap<>(5);
        // 获取日期
        Date date = getDate(dateStr);
        // 获取日期对应的天数，多少号
        int day = DateUtil.dayOfMonth(date) - 1;
        // 构建 Redis Key
        String signKey = buildSignKey(userId, date);
        // 查看今日是否已签到
        boolean isSigned = currentDaySign(userId, dateStr);
        // 根据当前日期统计签到次数
        Date today = new Date();
        // 统计当月连续签到次数
        int continuous = getContinuousSignCount(userId, today);
        // 统计当月总签到次数
        long count = getSumSignCount(userId, today);
        // 获取总签到次数
        long allSignCountByUser = 0;
        if (redisTemplate.opsForHash().get("allSignCountByUser", String.valueOf(userId)) != null) {
            allSignCountByUser = Long.valueOf((String) redisTemplate.opsForHash().get("allSignCountByUser", String.valueOf(userId)));
        }
        // 获取连续签到记录
        long continuousSignHistoryCount = 0;
        if (redisTemplate.opsForHash().get("continuousSignHistoryCount", String.valueOf(userId)) != null) {
            continuousSignHistoryCount = Long.valueOf((String) redisTemplate.opsForHash().get("continuousSignHistoryCount", String.valueOf(userId)));
        }
        result.put("today", isSigned);
        result.put("continuous", continuous);
        result.put("count", count);
        result.put("allSignCountByUser", allSignCountByUser);
        result.put("continuousSignHistoryCount", continuousSignHistoryCount);
        return result;
    }

    /*
    // 废弃方法
    public Map<String, Object> getSignInfo(Long userId, String dateStr) {
        // 获取日期
        Date date = getDate(dateStr);
        // 构建 Redis Key
        String signKey = buildSignKey(userId, date);
        // 构建一个自动排序的 Map
        Map<String, Object> signInfo = new TreeMap<>();
        // 获取某月的总天数（考虑闰年）
        int dayOfMonth = DateUtil.lengthOfMonth(DateUtil.month(date) + 1,
                DateUtil.isLeapYear(DateUtil.dayOfYear(date)));
        // e.g. bitfield user:sign:5:202103 u31 0
        BitFieldSubCommands bitFieldSubCommands = BitFieldSubCommands.create()
                .get(BitFieldSubCommands.BitFieldType.unsigned(dayOfMonth))
                .valueAt(0);
        // 获取用户从当前日期开始到 1 号的所有签到数据
        List<Long> list = redisTemplate.opsForValue().bitField(signKey, bitFieldSubCommands);
        if (list == null || list.isEmpty()) {
            return signInfo;
        }
        long v = list.get(0) == null ? 0 : list.get(0);
        // 从低位到高位进行遍历，为 0 表示未签到，为 1 表示已签到
        for (int i = dayOfMonth; i > 0; i--) {

            Map 存储格式：
                签到：  yyyy-MM-01 "✅"
                未签到：yyyy-MM-02 不做任何处理

            // 获取日期
            LocalDateTime localDateTime = LocalDateTimeUtil.of(date).withDayOfMonth(i);
            // 右移再左移，如果不等于自己说明最低位是 1，表示已签到
            boolean flag = v >> 1 << 1 != v;
            // 如果已签到，添加标记
            if (flag) {
                signInfo.put(DateUtil.format(localDateTime, "yyyy-MM-dd"), "✅");
            }
            // 右移一位并重新赋值，相当于把最低位丢弃一位然后重新计算
            v >>= 1;
        }
        return signInfo;
    }
    */

    /**
     * @param userId 用户id
     * @param today  日期
     * @return 当月总签到次数
     */
    private Long getSumSignCount(int userId, Date today) {
        String signKey = buildSignKey(userId, today);
        return (Long) redisTemplate.execute((RedisCallback<Long>) con -> con.bitCount(signKey.getBytes()));
    }

    /**
     * @param userId 用户id
     * @param date   日期
     * @return 当月连续签到次数
     */
    private int getContinuousSignCount(int userId, Date date) {
        // 获取日期对应的天数，默认31
        int dayOfMonth = DateUtil.dayOfMonth(date);
        // key
        String signKey = buildSignKey(userId, date);
        BitFieldSubCommands bitFieldSubCommands = BitFieldSubCommands.create()
                .get(BitFieldSubCommands.BitFieldType.unsigned(dayOfMonth))
                .valueAt(0);
        // 获取用户从当前日期开始到 1 号的所有签到状态
        List<Long> list = redisTemplate.opsForValue().bitField(signKey, bitFieldSubCommands);
        if (list == null || list.isEmpty()) {
            return 0;
        }
        // 连续签到计数器
        int signCount = 0;
        long v = list.get(0) == null ? 0 : list.get(0);
        // 位移计算连续签到次数
        for (int i = dayOfMonth; i > 0; i--) {
            // 右移再左移，如果等于自己说明最低位是 0，表示未签到
            if (v >> 1 << 1 == v) {
                // 用户可能当前还未签到，所以要排除是否是当天的可能性
                // 低位 0 且非当天说明连续签到中断了
                if (i != dayOfMonth) {
                    break;
                }
            } else {
                // 右移再左移，如果不等于自己说明最低位是 1，表示签到
                signCount++;
            }
            // 右移一位并重新赋值，相当于把最低位丢弃一位然后重新计算
            v >>= 1;
        }
        return signCount;
    }

    /**
     * user:sign:userId:yyyyMM
     *
     * @param userId 用户id
     * @param date   日期
     * @return 构建key
     */
    private String buildSignKey(int userId, Date date) {
        return String.format("user:sign:%d:%s", userId, DateUtil.format(date, "yyyyMM"));
    }

    /**
     * @param dateStr 日期
     * @return 获取格式化日期
     */
    private Date getDate(String dateStr) {
        return StrUtil.isBlank(dateStr) ? new Date() : DateUtil.parseDate(dateStr);
    }
}
