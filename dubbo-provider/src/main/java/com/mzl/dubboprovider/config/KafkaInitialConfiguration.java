//package com.mzl.dubboprovider.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.admin.NewTopic;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @ClassName :   KafkaInitialConfiguration
// * @Description: kafka配置类
// * @Author: mzl
// * @CreateDate: 2022/6/28 12:01
// * @Version: 1.0
// */
//@Configuration
//@Slf4j
//public class KafkaInitialConfiguration {
//
//    /**
//     * 创建一个名为testTopic的Topic并设置分区数partitions为8，分区副本数replication-factor为2
//     * @return
//     */
//    @Bean
//    public NewTopic initialTopic() {
//        log.info("begin to init initialTopic........................");
//        return new NewTopic("topic04",8, (short) 2 );
//    }
//
//  /**
//   * 如果要修改分区数，只需修改配置值重启项目即可
//   * 修改分区数并不会导致数据的丢失，但是分区数只能增大不能减小
//   * @return
//   */
//  @Bean
//  public NewTopic updateTopic() {
//        log.info("begin to init updateTopic........................");
//        return new NewTopic("topic04",10, (short) 2 );
//    }
//
//}
