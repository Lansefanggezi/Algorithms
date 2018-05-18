package com.kang.redis;

import redis.clients.jedis.Jedis;

public class Ping {

	public static void main(String[] args) {
		
		Jedis jedis = new Jedis("192.168.121.128",5000);
		System.out.println(jedis.ping());
	}
}
