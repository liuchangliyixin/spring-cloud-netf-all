package com.yixiu.advance.cloud.config.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Set;

@SpringBootApplication
@EnableScheduling
@EnableDiscoveryClient
public class ConfigClientApplication {

    private final ContextRefresher contextRefresher;

    private final Environment environment;

    public ConfigClientApplication(ContextRefresher contextRefresher, Environment environment) {
        this.contextRefresher = contextRefresher;
        this.environment = environment;
    }

    @Bean
    public MyHealthIndicator myHealthIndicator(){
        return new MyHealthIndicator();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class,args);
    }

    @Scheduled(fixedRate = 5000,initialDelay = 3000)
    public void autoRefresh(){
        Set<String> updatePropertiesNames= contextRefresher.refresh();
        /*if(!updatePropertiesNames.isEmpty()){
            System.out.printf("Thread : %s 当前配置已更新，具体项目: %s \n",Thread.currentThread().getName(),updatePropertiesNames);
        }*/

        updatePropertiesNames.forEach(property -> {
            System.out.printf("Thread : %s 当前配置已更新，具体项目 key: %s , value : %s \n",
                    Thread.currentThread().getName(),
                    property,
                    environment.getProperty(property)
            );
        });
    }
}
