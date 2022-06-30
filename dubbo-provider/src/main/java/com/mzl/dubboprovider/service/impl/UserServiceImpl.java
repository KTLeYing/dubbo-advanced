package com.mzl.dubboprovider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mzl.dubboapi.service.UserService;
import com.mzl.dubbocommon.entity.User;
import com.mzl.dubbocommon.response.RetResult;
import com.mzl.dubboprovider.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mzl
 * @since 2022-06-10
 */
@Service(timeout = 5000)
@Transactional
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * 查询所有用户
     * @return
     */
    @Override
    public RetResult findAllUser() {
        //休息12s，使此服务超时，达到测试超时的效果
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        return RetResult.success(userMapper.selectList(queryWrapper));
    }

    @Override
    public String syaHello() {
        return "你好！";
    }

    /**
     * 异步调用
     * @return
     */
    @Override
    public CompletableFuture<String> syaHelloAsync() {
        System.out.println("执行了异步服务(做蛋糕过程逻辑)...当前主线程：" + Thread.currentThread().getName());
    // 建议为supplyAsync提供自定义线程池，避免使用JDK公用线程池(师傅正在做蛋糕中，制作蛋糕过程)
    return CompletableFuture.supplyAsync(
        () -> {
          try {
            System.out.println("师傅准备做蛋糕..." + Thread.currentThread().getName());
            System.out.println("当前子线程：" + Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println("师傅做蛋糕做好了..." + Thread.currentThread().getName());
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          return "你好！";
        });
    }

    /**
     * kafka队列测试（生产者发送消息）
     * @return
     */
    @Override
    public RetResult kafkaSend(String topic, String msg) {
        //发送消息，topic为要发送的队列名(主题名)， msg为要发送的消息
        kafkaTemplate.send(topic, msg);
        return RetResult.success("发送消息成功");
    }

    /**
     * kafka队列测试（生产者发送消息，有配置类）
     * @param msg
     * @return
     */
    @Override
    public RetResult kafkaSend1(String msg) {
        try{
            ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("topic04", msg);
            //生产者发送消息后回调，异步等待响应
            future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
                //发送失败后的处理
                @Override
                public void onFailure(Throwable ex) {
                    log.info("topic04 - 生产者 发送消息失败：" + ex.getMessage());
                }
                //发送成功后的处理，打印发送结果
                @Override
                public void onSuccess(SendResult<String, Object> result) {
                    log.info("topic04 - 生产者 发送消息成功：" + result.toString());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RetResult.success("发送消息成功");
    }

    /**
     * kafka队列测试（生产者发送消息，有配置类，同步发送）
     * @param msg
     * @return
     */
    @Override
    public RetResult kafkaSendSync(String msg) {
        //生产者发送的消息记录封装
        ProducerRecord<Integer, String> record = new ProducerRecord<>("topic-spring-02", 0, 1, msg);
        ListenableFuture future = kafkaTemplate.send(record);
        try {
            //生产者发送消息后回调, 同步等待broker的响应
            Object o = future.get();
            SendResult<Integer, String> result = (SendResult<Integer, String>) o;
            log.info("生产者发送的消息数据：" + result.getRecordMetadata().topic() + "-----" + result.getRecordMetadata().partition() + "-----" + result.getRecordMetadata().offset());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return RetResult.success("发送消息成功");
    }

    /**
     * kafka队列测试（生产者发送消息，有配置类，异步发送）
     * @param msg
     * @return
     */
    @Override
    public RetResult kafkaSendAsync(String msg) {
        //生产者发送的消息记录封装
        ProducerRecord<Integer, String> record = new ProducerRecord<>("topic-spring-03", 0, 1, msg);
        ListenableFuture future = kafkaTemplate.send(record);
        try{
            //生产者发送消息后回调，异步等待响应
            future.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
                @Override
                public void onFailure(Throwable throwable) {
                    log.info("发送失败：" + throwable.getMessage());
                }

                @Override
                public void onSuccess(SendResult<Integer, String> result) {
                    log.info("生产者发送的消息数据：" + result.getRecordMetadata().topic() + "-----" + result.getRecordMetadata().partition() + "-----" + result.getRecordMetadata().offset());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RetResult.success("发送消息成功");
    }


}
