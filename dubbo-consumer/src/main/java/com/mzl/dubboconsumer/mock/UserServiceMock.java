package com.mzl.dubboconsumer.mock;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mzl.dubboapi.service.UserService;
import com.mzl.dubbocommon.entity.User;
import com.mzl.dubbocommon.enums.RetCodeEnum;
import com.mzl.dubbocommon.response.RetResult;
import org.apache.ibatis.jdbc.Null;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * @ClassName :   UserServiceMock
 * @Description: 自定义服务降级的处理实现类,实现服务接口,定义消费者(客户端)具体降级逻辑
 * @Author: mzl
 * @CreateDate: 2022/6/14 10:24
 * @Version: 1.0
 */
public class UserServiceMock implements UserService {

    /**
     * 实现服务查询所有用户接口降级
     * @return
     */
    @Override
    public RetResult findAllUser() {
        return RetResult.success(RetCodeEnum.SERVICE_DEGRADE);
    }

    @Override
    public String syaHello() {
        return "服务降级";
    }

    /**
     * 异步调用
     * @return
     */
    @Override
    public CompletableFuture<String> syaHelloAsync() {
        System.out.println("执行了异步服务");
        //相当于在异步线程里执行sayHello方法！
        return CompletableFuture.supplyAsync(() -> {
            return syaHello();
        });
    }

    @Override
    public RetResult kafkaSend(String topic, String msg) {
        return null;
    }

    /**
     * kafka队列测试（生产者发送消息，有配置类）
     * @param msg
     * @return
     */
    @Override
    public RetResult kafkaSend1(String msg) {
        return null;
    }

    /**
     * kafka队列测试（生产者发送消息，有配置类）
     * @param msg
     * @return
     */
    @Override
    public RetResult kafkaSendSync(String msg) {
        return null;
    }

    /**
     * kafka队列测试（生产者发送消息，有配置类，异步发送）
     * @param msg
     * @return
     */
    @Override
    public RetResult kafkaSendAsync(String msg) {
        return null;
    }

    /**
     * Service接口实现了Mybatis-plus的IService，所以要覆盖里面的接口方法
     * @param entityList
     * @param batchSize
     * @return
     */
    @Override
    public boolean saveBatch(Collection<User> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<User> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<User> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(User entity) {
        return false;
    }

    @Override
    public User getOne(Wrapper<User> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<User> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<User> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<User> getBaseMapper() {
        return null;
    }

    @Override
    public Class<User> getEntityClass() {
        return null;
    }
}
