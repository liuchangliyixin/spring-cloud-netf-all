package com.yixiu.advance.boot.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringEventListenerDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册监听器
        context.addApplicationListener(new ApplicationListener<MyApplicationEvent>() {
            //监听器收到事件
            @Override
            public void onApplicationEvent(MyApplicationEvent event) {
                System.out.println("Accept Event : " + event.getSource() + " #application context: "+ event.getApplicationContext());
            }
        });
        context.refresh();
        //发布事件
        context.publishEvent(new MyApplicationEvent(context,"Hello World!"));
    }

    private static class MyApplicationEvent extends ApplicationEvent{
        //在监听器中获取Spring的上下文
        private final ApplicationContext applicationContext;
        public MyApplicationEvent(ApplicationContext applicationContext,Object source) {
            super(source);
            this.applicationContext = applicationContext;
        }

        public ApplicationContext getApplicationContext() {
            return applicationContext;
        }
    }
}
