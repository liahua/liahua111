package com.jt.idea.manage.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class RedisAop {

    @Autowired
    private JedisCluster jedisCluster;
    @Autowired
    private ObjectMapper objectMapper;

    public void findItemAllFromCache(ProceedingJoinPoint jp) throws IOException {
        /**
         * 只有一个参数
         */

        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        Object[] args = jp.getArgs();
        String parentId = args[0] + "";
        System.out.println("parentId"+parentId);

        String easyUITreeList = jedisCluster.get(parentId);
        if (StringUtils.isEmpty(easyUITreeList)) {

            Object result = null;
            try {
                result = jp.proceed(args);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            try {
                easyUITreeList = objectMapper.writeValueAsString(result);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            jedisCluster.set(parentId, easyUITreeList);
        }
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(easyUITreeList);
        out.close();
    }
}
