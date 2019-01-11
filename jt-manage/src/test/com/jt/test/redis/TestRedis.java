package com.jt.test.redis;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.idea.manage.pojo.User;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public class TestRedis {


    @Test
    public void testString() {
        Jedis jedis = new Jedis("176.198.114.154", 6379);
        jedis.set("1809", "学习redis");
        String s = jedis.get("1809");
        System.out.println("获取数据" + s);
    }


    @Test
    public void testHash() {
        Jedis jedis = new Jedis("176.198.114.154", 6379);
        jedis.hset("person", "name", "西门大官人");
        jedis.hset("person", "age", "25");
        Map<String, String> perMap = jedis.hgetAll("person");
        System.out.println(perMap);
    }

    @Test
    public void toJson() throws IOException {
        Jedis jedis = new Jedis("176.198.114.154", 6379);
        User user = new User();
        user.setAge(1);
        user.setId(1);
        user.setName("111");
        user.setSex("sex");
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(user);
        System.out.println("objectMapper:"+s);
        jedis.set("user", s);
        String user2 = jedis.get("user");
        System.out.println("user2"+user2);
        User user1 = objectMapper.readValue(user2, User.class);
        System.out.println(user1.getName());
    }

    @Test
    public void testShards() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(2000);
        ArrayList<JedisShardInfo> jedisShardInfos = new ArrayList<>();
        jedisShardInfos.add(new JedisShardInfo("176.198.114.154", 6379));
        jedisShardInfos.add(new JedisShardInfo("176.198.114.154", 6380));
        jedisShardInfos.add(new JedisShardInfo("176.198.114.154", 6381));
        ShardedJedisPool shardedJedisPool = new ShardedJedisPool(jedisPoolConfig,jedisShardInfos);
        ShardedJedis resource = shardedJedisPool.getResource();
        resource.set("1809", "学习redis");
        resource.set("sssssssssss","ceshi");
        //可以做set了
    }

    @Test
    public void testPool(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/*.xml");
        JedisPoolConfig jedisPoolConfig = ctx.getBean("jedisPoolConfig", JedisPoolConfig.class);
        System.out.println("jedisPoolConfig完毕");
        HashSet<HostAndPort> hostAndPorts = new HashSet<>();
        for (int port=7000;port<7009;port++){
            hostAndPorts.add(new HostAndPort("176.198.114.153", port));
        }
        System.out.println("循环完毕");
        JedisCluster jedisCluster = new JedisCluster(hostAndPorts,jedisPoolConfig);
        System.out.println("对象建立完毕");
        jedisCluster.set("1809", "学习redis");
        System.out.println("set完毕");

    }


}
