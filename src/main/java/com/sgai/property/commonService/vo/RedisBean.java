package com.sgai.property.commonService.vo;

import java.io.Serializable;

/**
 * redisbean
 *
 * @author Hou
 * @create 2018-03-12 13:59
 */
public class RedisBean implements Serializable{

    private String key;
    private Object obj;

    private Long time;

    public Object getObj() {
        return obj;
    }

    public RedisBean setObj(Object obj) {
        this.obj = obj;
        return this;
    }

    public Long getTime() {
        return time;
    }

    public RedisBean setTime(Long time) {
        this.time = time;
        return this;
    }

    @Override
    public String toString() {
        return "RedisBean{" +
                "key='" + key + '\'' +
                ", obj=" + obj +
                ", time=" + time +
                '}';
    }

    public String getKey() {
        return key;
    }

    public RedisBean setKey(String key) {
        this.key = key;
        return this;
    }
}
