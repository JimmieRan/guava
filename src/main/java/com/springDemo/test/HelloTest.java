package com.springDemo.test;

import com.springDemo.service.HelloApiImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2016/7/8.
 */
public class HelloTest {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-config.xml");
        HelloApiImpl h = ac.getBean("hello", HelloApiImpl.class);
        h.seyHello();
    }
}
