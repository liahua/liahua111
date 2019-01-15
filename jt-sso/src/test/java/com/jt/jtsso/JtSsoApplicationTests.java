package com.jt.jtsso;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisCluster;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JtSsoApplicationTests {
    @Autowired
    JedisCluster jedisCluster;

    @Test
    public void contextLoads() {
        jedisCluster.set("test", "test");
        System.out.println("连接成功");
        System.out.println(11);
    }

}

