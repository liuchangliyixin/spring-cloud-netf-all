package com.yixiu.eureka.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@EnableAutoConfiguration
@EnableDiscoveryClient
@RestController
public class EurekaClientBootstrap {

    @Autowired
    private DiscoveryClient discoveryClient;

    //对应Eureka的Rest接口：http://${host}:${port}/eureka/apps
    @RequestMapping(value = "/services",method = RequestMethod.GET)
    public Set<String> getServices(){
        //使用LinkedHashSet保证返回的顺序
        return new LinkedHashSet<>(discoveryClient.getServices());
    }

    //对应Eureka的Rest接口：http://${host}:${port}/eureka/apps/${app_name}
    @RequestMapping(value = "/services/{serviceName}",method = RequestMethod.GET)
    public List<ServiceInstance> getInstances(@PathVariable(value = "serviceName") String name){
        return discoveryClient.getInstances(name);
    }

    //对应Eureka的Rest接口：http://${host}:${port}/eureka/apps/${app_name}/${app_instance_id}
    @GetMapping("/services/{serviceName}/{serviceInstance}")
    public ServiceInstance getInstance(@PathVariable String serviceName,@PathVariable String serviceInstance){
        return getInstances(serviceName)
                .stream()
                .filter(serviceInstance1 -> serviceInstance.equals(serviceInstance1.getInstanceId()))
                .findFirst()
                .orElseThrow( () -> new RuntimeException("No service match !"));
    }

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientBootstrap.class,args);
    }
}
