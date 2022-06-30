package com.mzl.dubboprovider.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @ClassName :   KafkaConsumer
 * @Description:  kafka队列消费者
 * @Author: mzl
 * @CreateDate: 2022/6/25 15:04
 * @Version: 1.0
 */
@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = {"topic1"})
    public void kafkaConsume(ConsumerRecord<String, String> record){
        //获取消息
        String message = record.value();
        //获取消息偏移量
        long offset = record.offset();
        log.info("读取到的消息为：" + message + "-----" + "当前偏移量为：" + offset);
    }

    @KafkaListener(topics = {"topic04"})
    public void kafkaConsumer1(ConsumerRecord<?, ?> record){
        //获取消息
        String message = (String) record.value();
        //获取消息偏移量
        long offset = record.offset();
        //获取分区
        Integer partition = record.partition();
        //获取主题
        String topic = record.topic();
        //insertIntoDb(buffer);//这里为插入数据库代码
        log.info("读取到的消息为：" + message + "-----" + "当前偏移量为：" + offset +
                "-----" + "分区为：" + offset + "-----" + "主题为：" + offset);
    }

    /**
     * 同步发送的消费者监听【同步发送】
     * @param record
     */
    @KafkaListener(topics = {"topic-spring-02"})
    public void kafkaConsumerSync(ConsumerRecord<?, ?> record){
        Optional<ConsumerRecord<?, ?>> optional = Optional.ofNullable(record);
        //如果消息存在，则处理
        if (optional.isPresent()){
            //获取消息
            String message = (String) record.value();
            //获取消息偏移量
            long offset = record.offset();
            //获取分区
            Integer partition = record.partition();
            //获取主题
            String topic = record.topic();
            //insertIntoDb(buffer);//这里为插入数据库代码
            log.info("读取到的消息为：" + message + "-----" + "当前偏移量为：" + offset +
                    "-----" + "分区为：" + offset + "-----" + "主题为：" + offset);
        }
    }

    /**
     * 异步发送的消费者监听【异步发送】
     * @param record
     */
    @KafkaListener(topics = {"topic-spring-03"})
    public void kafkaConsumerAsync(ConsumerRecord<?, ?> record){
        Optional<ConsumerRecord<?, ?>> optional = Optional.ofNullable(record);
        //如果消息存在，则处理
        if (optional.isPresent()){
            //获取消息
            String message = (String) record.value();
            //获取消息偏移量
            long offset = record.offset();
            //获取分区
            Integer partition = record.partition();
            //获取主题
            String topic = record.topic();
            //insertIntoDb(buffer);//这里为插入数据库代码
            log.info("读取到的消息为：" + message + "-----" + "当前偏移量为：" + offset +
                    "-----" + "分区为：" + offset + "-----" + "主题为：" + offset);
        }
    }

}
