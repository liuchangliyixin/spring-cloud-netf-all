package com.yixiu.action.ribbonclient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
public class HelloService {

    private static final Random random = new Random();

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallBack",
            commandProperties = {
                @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="100")
    })
    public String helloConsumer() throws InterruptedException {
        //随机时间超过100s将调用失败方法
        int val = random.nextInt(200);
        System.out.println("helloConsumer() costs " + val + " s");
        Thread.sleep(val);

        return restTemplate.getForEntity("http://EUREKA-CLIENT/hello",String.class).getBody();
    }

    public String helloFallBack(){
        return "error route via";
    }
}
