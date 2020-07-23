package com.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Zm-Mmm
 */
public class GetIp {

    private static final Logger logger = LoggerFactory.getLogger(GetIp.class);

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        String unknown = "unknown";
        String dh = ",";
        logger.info("x-forwarded-for ip: " + ip);
        if (ip != null && ip.length() != 0 && !unknown.equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.indexOf(dh) != -1) {
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            logger.info("Proxy-Client-IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            logger.info("WL-Proxy-Client-IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
            logger.info("HTTP_CLIENT_IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            logger.info("HTTP_X_FORWARDED_FOR ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
            logger.info("X-Real-IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            logger.info("getRemoteAddr ip: " + ip);
        }
        return ip;
    }
}
