package com.bean;

import java.util.Arrays;

/**
 * @author Zm-Mmm
 */
public class LogBean {
    private String url;
    private String ip;
    private String method;
    private Object[] args;

    public LogBean(String url, String ip, String method, Object[] args) {
        this.url = url;
        this.ip = ip;
        this.method = method;
        this.args = args;
    }

    @Override
    public String toString() {
        return "{" +
                "url='" + url + '\'' +
                ", ip='" + ip + '\'' +
                ", method='" + method + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
