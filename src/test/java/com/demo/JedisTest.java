package com.demo;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class JedisTest {
	
	@Test
	public void test1() {
		
		Jedis jedis = new Jedis("127.0.0.1",6379);
		String name = jedis.get("name");
		System.out.println(name);
		jedis.close();
		
	}
	
}
