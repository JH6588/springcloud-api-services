package com.hd;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@RefreshScope
public class ApiGatewayApplication implements BeanPostProcessor {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }


}
