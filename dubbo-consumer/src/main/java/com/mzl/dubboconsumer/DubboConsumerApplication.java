package com.mzl.dubboconsumer;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableDubbo
@EnableAsync
public class DubboConsumerApplication {

  public static void main(String[] args) {
    SpringApplication.run(DubboConsumerApplication.class, args);
  }
}
