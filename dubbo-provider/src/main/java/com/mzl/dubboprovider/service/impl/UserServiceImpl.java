package com.mzl.dubboprovider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mzl.dubboapi.service.UserService;
import com.mzl.dubbocommon.entity.User;
import com.mzl.dubbocommon.response.RetResult;
import com.mzl.dubboprovider.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mzl
 * @since 2022-06-10
 */
@Service(timeout = 5000, async = true)
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

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

}
