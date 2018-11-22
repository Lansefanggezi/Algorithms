package com.kang.redis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class DoubleCheckLock {
	private static volatile JedisPool jedisPool = null;
	
	private DoubleCheckLock(){}
	
	public static JedisPool getJedisPoolInstance(){
		if(null ==jedisPool){
			synchronized (DoubleCheckLock.class) {
				if (null == jedisPool) {
					JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
					jedisPoolConfig.setMaxActive(1000);
					jedisPoolConfig.setMaxIdle(32);
					jedisPoolConfig.setMaxWait(100*1000);
					jedisPoolConfig.setTestOnBorrow(true);
					jedisPool = new JedisPool(jedisPoolConfig,"192.168.121.128",6379);
				}
			}
		}
		return jedisPool;
	}
}
