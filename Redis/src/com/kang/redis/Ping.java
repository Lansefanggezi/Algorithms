package com.kang.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Ping {

	public static void main(String[] args) {
		JedisPool jedisPool = DoubleCheckLock.getJedisPoolInstance();
		Jedis jedis = jedisPool.getResource();
		jedis.auth("kang");
		System.out.println(jedis.ping());
	}
}
