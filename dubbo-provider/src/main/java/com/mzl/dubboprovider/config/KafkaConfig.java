package com.mzl.dubboprovider.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName :   KafkaConfig
 * @Description: kafka初始化配置类
 * @Author: mzl
 * @CreateDate: 2022/6/29 1:13
 * @Version: 1.0
 */
@Configuration
@Slf4j
public class KafkaConfig {

    /**
     * 第一个是参数是topic名字，第二个参数是分区个数，第三个是topic的复制因子个数
     * 当broker个数为1个时会创建topic失败，
     * 只有在集群中才能使用kafka的备份功能
     * @return
     */
    @Bean
    public NewTopic topic1() {
        log.info("begin to init topic1........................");
        return new NewTopic("topic1", 5, (short) 1);
    }

    @Bean
    public NewTopic topic2() {
        log.info("begin to init topic2........................");
        return new NewTopic("topic2", 3, (short) 1);
    }


}
