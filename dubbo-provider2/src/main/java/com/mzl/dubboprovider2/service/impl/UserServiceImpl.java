package com.mzl.dubboprovider2.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mzl.dubboapi.service.UserService;
import com.mzl.dubbocommon.entity.User;
import com.mzl.dubbocommon.response.RetResult;
import com.mzl.dubboprovider2.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 查询所有用户
     * @return
     */
    @Override
    public RetResult findAllUser() {
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
        System.out.println("执行了异步服务");
        //相当于在异步线程里执行sayHello方法！
        return CompletableFuture.supplyAsync(() -> {
            return syaHello();
        });
    }

}
