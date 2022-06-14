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

}
