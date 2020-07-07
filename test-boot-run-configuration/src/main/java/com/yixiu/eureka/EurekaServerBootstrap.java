package com.yixiu.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

public class EurekaServerBootstrap {

    @EnableAutoConfiguration
    @EnableEurekaServer
    public static class EurekaServerConfiguration{

    }

    public static void main(String[] args) {
        //这里传配置类即可，不一定是主类，因为上面两个注解我们一般写在主类上，所以这里才会写主类
        //按照上面的写法，我们写一个内部配置类，这里就可以写内部配置类
        SpringApplication.run(EurekaServerConfiguration.class,args);
    }
}
