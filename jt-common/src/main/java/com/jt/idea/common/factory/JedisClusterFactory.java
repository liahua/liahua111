package com.jt.idea.common.factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;


public class JedisClusterFactory implements FactoryBean<JedisCluster> {

    private Set<HostAndPort> hostAndPorts =new HashSet<>();

    private Resource resource;

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Autowired
    private JedisPoolConfig jedisPoolConfig;

    @Override
    public JedisCluster getObject() throws Exception {
        getNodes();
        return new JedisCluster(hostAndPorts, jedisPoolConfig);
    }

    private void getNodes() {
        Properties properties = new Properties();
        try {
            properties.load(resource.getInputStream());
            Set<Object> keys = properties.keySet();
            for (Object key : keys) {
                String keyName=(String)key;
                if (keyName.startsWith("redis.cluster")){
                    String value = (String) properties.get(key);
                    int i = value.indexOf(":");
                    String host=value.substring(0,i);
                    int port =Integer.parseInt(value.substring(i+1));

                    this.hostAndPorts.add(new HostAndPort(host, port));

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Class<?> getObjectType() {
        return JedisCluster.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
