package com.poom.quest.web.config;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

//@Configuration
public class RedisConfiguration {
  
  @Bean
  JedisConnectionFactory jedisConnectionFactory() {
    JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
    jedisConnectionFactory.setHostName("localhost");
    jedisConnectionFactory.setPort(80);
    jedisConnectionFactory.setUsePool(true);
    return jedisConnectionFactory;
  }
  
  @Bean
  StringRedisTemplate redisTemplate() {
    StringRedisTemplate redisTemplate = new StringRedisTemplate();
    redisTemplate.setConnectionFactory(jedisConnectionFactory());
    return redisTemplate;
  }
  
  @Bean
  CacheManager cacheManager() {
    return new RedisCacheManager(redisTemplate());
  }

}
