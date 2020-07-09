package com.yixiu.action.zuul.client;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringCloudApplication
@EnableZuulProxy
public class ZuulClient {
    public static void main(String[] args) {
        SpringApplication.run(ZuulClient.class,args);
    }

    @Bean
    public SampleFilter sampleFilter(){
        return new SampleFilter();
    }
}
