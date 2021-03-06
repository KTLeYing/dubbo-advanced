package com.mzl.dubboapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mzl.dubbocommon.entity.User;
import com.mzl.dubbocommon.response.RetResult;

import java.util.concurrent.CompletableFuture;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mzl
 * @since 2022-06-10
 */
public interface UserService extends IService<User> {

    /**
     * 查询所有用户
     * @return
     */
    RetResult findAllUser();

    /**
     * 服务降级测试
     * @return
     */
    String syaHello();

    /**
     * 异步调用
     * @return
     */
    CompletableFuture<String> syaHelloAsync();

    /**
     * kafka队列测试（生产者发送消息）
     * @return
     */
    RetResult kafkaSend(String topic, String msg);

    /**
     * kafka队列测试（生产者发送消息，有配置类）
     * @param msg
     * @return
     */
    RetResult kafkaSend1(String msg);

    /**
     * kafka队列测试（生产者发送消息，有配置类，同步发送）
     * @param msg
     * @return
     */
    RetResult kafkaSendSync(String msg);

    /**
     * kafka队列测试（生产者发送消息，有配置类，异步发送）
     * @param msg
     * @return
     */
    RetResult kafkaSendAsync(String msg);
}
