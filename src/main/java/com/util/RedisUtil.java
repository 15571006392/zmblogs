package com.util;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author Zm-Mmm
 */
public class RedisUtil {

    /**
     * 清空redis中的留言相关数据
     * @param redisTemplate redis
     */
    public static void flushRedisLeavingMessage(RedisTemplate redisTemplate){
        redisTemplate.opsForHash().delete("menu","message");
    }

    /**
     * 清空redis中的types相关数据
     * @param redisTemplate redis
     */
    public static void flushRedisTypes(RedisTemplate redisTemplate){
        // 清空首页分类redis缓存
        redisTemplate.opsForHash().delete("index","types");
        // 清空所有分类redis缓存
        redisTemplate.opsForHash().delete("menu","types");
    }

    /**
     * 清空redis中的tags相关数据
     * @param redisTemplate redis
     */
    public static void flushRedisTags(RedisTemplate redisTemplate){
        // 清空首页标签redis缓存
        redisTemplate.opsForHash().delete("index","tags");
        // 清空所有标签redis缓存
        redisTemplate.opsForHash().delete("menu","tags");
    }

    /**
     * 清空所有redis缓存中的menu数据
     * @param redisTemplate redis
     */
    public static void flushAllRedisMenu(RedisTemplate redisTemplate){
        // 清空所有分类、标签redis缓存
        redisTemplate.opsForHash().delete("menu","types");
        redisTemplate.opsForHash().delete("menu","tags");
        // 清空归档页博客redis缓存
        redisTemplate.opsForHash().delete("menu","archiveCount");
        redisTemplate.opsForHash().delete("menu","archiveBlogs");
    }

    /**
     * 清空所有redis缓存中的index数据
     * @param redisTemplate redis
     */
    public static void flushAllRedisIndex(RedisTemplate redisTemplate){
        // 清空首页分类、标签、推荐博客redis缓存
        redisTemplate.opsForHash().delete("index", "tags");
        redisTemplate.opsForHash().delete("index", "types");
        redisTemplate.opsForHash().delete("index", "recommends");
    }
}
