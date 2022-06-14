package com.mzl.dubboconsumer.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.mzl.dubboapi.service.UserService;
import com.mzl.dubbocommon.response.RetResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器,用户控制器
 * </p>
 *
 * @author mzl
 * @since 2022-06-10
 */
@RestController
@RequestMapping("user")
@Api(value = "用户模块接口", tags = "用户模块接口")
public class UserController {

    //加上check = false，false：表示无提供者应用启动会成功，不然服务会报500，检查不到服务提供者
//    @Reference(check = false, mock = "com.mzl.dubboconsumer.mock.UserServiceMock")
    //指定mock策略为布尔类型,且为 true,当调用远程服务失败后, 就会执行调用其他provider
//    @Reference(check = false, mock = "true")
    //指定mock策略为抛出自定义异常, 当远程服务调用失败后, 会给服务消费者抛出自定义异常.
//    @Reference(check = false, mock = "throw com.mzl.dubboconsumer.exception.CustomException", timeout = 1)
    //指定 mock 属性值为返回 mock 数据，当远程服务调用失败后，就会给服务消费者返回 null，(mock数据可以不写,默认返回null)
    @Reference(check = false, mock = "return 服务降级", timeout = 10000)
    private UserService userService;

    /**
     *查询所有用户
     * @return
     */
    @GetMapping("/findAllUser")
    @ApiOperation(value = "查询所有用户")
    public RetResult findAllUser(){
        return userService.findAllUser();
    }

    /**
     * 服务降级测试
     * @return
     */
    @GetMapping("/sayHello")
    @ApiOperation(value = "服务降级测试")
    public String sayHello(){
        return userService.syaHello();
    }

}

