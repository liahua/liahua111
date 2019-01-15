package com.jt.jtsso.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@PropertySource("classpath:properties/redis.properties")
public class RedisPoolConf {
    @Value("${redis.minIdle}")
    private int minIdle;
    @Value("${redis.maxIdle}")
    private int maxIdLE;
    @Value("${redis.maxWait}")
    private int maxWaitMillis;
    @Value("${redis.maxTotal}")
    private int maxTotal;
    @Value("${redis.testOnBorrow}")
    private boolean testOnBorrow;


    @Bean("jedisPoolConf")
    public JedisPoolConfig getJedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxIdle(maxIdLE);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        jedisPoolConfig.setMaxTotal(maxTotal);
        return jedisPoolConfig;
    }
}
