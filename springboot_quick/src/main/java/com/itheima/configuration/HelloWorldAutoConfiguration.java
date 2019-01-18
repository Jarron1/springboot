package com.itheima.configuration;

import com.itheima.annotation.EnableHelloWorld;
import com.itheima.conditionl.ConditionalOnSystemProperty;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration //注解装配
@EnableHelloWorld//模块装配
@ConditionalOnSystemProperty(name = "user.name",value = "Mercy")
public class HelloWorldAutoConfiguration {





}
