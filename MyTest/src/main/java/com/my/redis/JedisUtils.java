package com.my.redis;

import redis.clients.jedis.Jedis;

public class JedisUtils {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("", 1);
        System.out.println("test");
//        jedis.set()
//        jedis.blpop()
    }
}
