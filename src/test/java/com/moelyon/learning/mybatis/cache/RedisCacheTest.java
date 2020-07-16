package com.moelyon.learning.mybatis.cache;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulRedisConnection;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class RedisCacheTest  {

    private final String key = "ljmgogo";
    RedisClient redisClient;
    StatefulRedisConnection<String,String> con;
    @Before
    public void init(){
        redisClient = RedisClient.create("redis://@127.0.0.1:6379/0?timeout=10s");
        con = redisClient.connect();
    }

    @Test
    public void  testRedisSync(){
        // format redis://password@host:port/0?timeout=10s
        var redisClient = RedisClient.create("redis://@127.0.0.1:6379/0?timeout=10s");
        var con = redisClient.connect();

        var d = con.sync();
        //d.auth("moelyon123");
        d.hsetnx(key,"ljm","gg");

        var map = d.hgetall(key);

        System.out.println(map);
    }

    @Test
    public void testAsync(){


        var d = con.async();

        d.hset(key,"ljm",new Date().toString());
        var map = d.hgetall(key);
        RedisFuture<String> f =  d.set("66","qaq");
        try {
            f.await(12, TimeUnit.SECONDS);
            f.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(map.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}