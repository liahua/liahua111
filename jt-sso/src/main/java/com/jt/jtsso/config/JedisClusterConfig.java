package com.jt.jtsso.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;


@Configuration
@PropertySource("classpath:properties/redis.properties")
@Data
public class JedisClusterConfig {
    @Value("${redis.cluster}")
    private String hostAndPorts;
    @Autowired
    private JedisPoolConfig jedisPoolConf;


    @Bean("jedisCluster")
    public JedisCluster getJedisCluster(){
        Set<HostAndPort> nodes=getHostAndPort(hostAndPorts);
        return new JedisCluster(nodes,jedisPoolConf);
    }

    private Set<HostAndPort> getHostAndPort(String hostAndPorts) {
        Set<HostAndPort> nodes= new HashSet<>();
        String[] hostAndPort = hostAndPorts.split(",");
        for (String s : hostAndPort) {
            String[] split = s.split(":");
            nodes.add(new HostAndPort(split[0], Integer.parseInt(split[1])));
        }
        return nodes;
    }

}
