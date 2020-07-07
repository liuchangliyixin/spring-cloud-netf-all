package com.yixiu.action.feignclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/feign-consumer")
    public String helloConsumer(){
        return helloService.hello();
    }

    @GetMapping("/feign-consumer2")
    public String hello2(){
        StringBuilder sb = new StringBuilder();
        sb.append(helloService.hello()).append("\n")
                .append(helloService.hello("David")).append("\n")
                .append(helloService.hello("Bob",22)).append("\n")
                .append(helloService.hello(new User("Mike",33))).append("\n");
        return sb.toString();
    }
}
