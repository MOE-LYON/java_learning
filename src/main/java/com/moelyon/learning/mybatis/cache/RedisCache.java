package com.moelyon.learning.mybatis.cache;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import org.apache.ibatis.cache.Cache;

public class RedisCache implements Cache {


    private final String id;
    private final RedisClient redisClient;
    public RedisCache(String id) {
        this.id = id;
        redisClient = RedisClient.create("redis://localhost/0");
    }

    public static void main(String[] args) {

    }

    public StatefulRedisConnection<String, String> getConnect(){
        StatefulRedisConnection<String, String> connection = redisClient.connect();
       return  connection;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void putObject(Object key, Object value) {

    }

    @Override
    public Object getObject(Object key) {
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int getSize() {
        return 0;
    }
}
