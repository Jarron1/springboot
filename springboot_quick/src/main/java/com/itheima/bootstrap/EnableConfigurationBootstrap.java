package com.itheima.bootstrap;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@EnableAutoConfiguration //激活自动装配
public class EnableConfigurationBootstrap {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = new SpringApplicationBuilder(EnableConfigurationBootstrap.class)

                .web(WebApplicationType.NONE)
                .run(args);


        String bean = context.getBean("hellWorld", String.class);

        System.out.println(bean);


        //关闭上下文
        context.close();

    }
}
