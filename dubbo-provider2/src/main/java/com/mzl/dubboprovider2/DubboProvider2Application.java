package com.mzl.dubboprovider2;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class DubboProvider2Application {

  public static void main(String[] args) {
    SpringApplication.run(DubboProvider2Application.class, args);
  }
}
