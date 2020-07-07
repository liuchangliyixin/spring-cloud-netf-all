package com.yixiu.action.eurekaclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class HelloController {
    private final Logger logger = Logger.getLogger("HelloAPP");

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/hello")
    public String index(){
        List<ServiceInstance> serviceInstance = discoveryClient.getInstances("EUREKA-CLIENT");
        serviceInstance.stream().forEach( instance-> {
            logger.info("HelloAPP: Host: " + instance.getHost() + " InstanceId: " + instance.getInstanceId());
        });

        return "Hello!";
    }

    @GetMapping("/hello1")
    public String hello(@RequestParam String name){
        return "Hello " + name;
    }

    @GetMapping("/hello2")
    public User hello(@RequestHeader String name,@RequestHeader int age){
        return new User(name,age);
    }

    @PostMapping("hello3")
    public String hello(@RequestBody User user){
        return user.toString();
    }
}
