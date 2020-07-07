package com.yixiu.action.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("eureka-client")
public interface HelloService {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    String hello();

    @GetMapping("/hello1")
    String hello(@RequestParam String name);

    @GetMapping("/hello2")
    User hello(@RequestHeader String name,@RequestHeader int age);

    @PostMapping("/hello3")
    String hello(@RequestBody User user);
}
