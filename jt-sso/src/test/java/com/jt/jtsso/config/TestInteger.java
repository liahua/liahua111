package com.jt.jtsso.config;

import org.junit.Test;

public class TestInteger {
    @Test
    public void testInteger(){
        Integer id=200;
        Integer ids=200;
        if (id==ids){
            System.out.println("result:"+true);
        }else {
            System.out.println("result"+false);
        }
    }
}
