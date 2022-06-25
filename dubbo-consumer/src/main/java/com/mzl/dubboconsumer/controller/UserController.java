package com.mzl.dubboconsumer.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.mzl.dubboapi.service.UserService;
import com.mzl.dubbocommon.response.RetResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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
//    @Reference(check = false, mock = "return 服务降级", timeout = 10000)
    //故障转移策略,retries=4, dubbo默认的retries是2次，默认的的集群容错策略是failover
//    @Reference(check = false, retries = 2, timeout = 10000)
    //快速失败策略，消费者端只发起一次调用，若失败则立即报错，就直接返回错误，怒不会重试了。
//    @Reference(check = false, cluster = "failfast", timeout = 10000)
    //失败安全策略，当消费者调用提供者出现异常时，直接忽略本次消费操作，如果方法有返回值就会直接返回null。
//    @Reference(check = false, cluster = "failsafe", timeout = 10000)
    //失败自动恢复策略，当消费者调用提供者失败后，Dubbo会记录下该失败请求，然后定时自动重新发送该请求。
//    @Reference(check = false, cluster = "failback", timeout = 10000)
    //并行策略。消费者对于同一服务并行调用多个提供者服务器，只要一个成功即调用结束并返回结果。
//    @Reference(check = false, cluster = "forking", timeout = 10000)
    //广播策略。广播调用所有提供者，逐个调用，任意一台报错则报错。
    //随机策略，按权重设置随机概率。
//    @Reference(check = false, loadbalance = "random", timeout = 10000)
    //轮询策略，按照权重设置轮循比率。
//    @Reference(check = false, loadbalance = "roundrobin", timeout = 10000)
    //最少活跃调用数，相同活跃数的随机，活跃数指调用前后计数差。
//    @Reference(check = false, loadbalance = "leastactive", timeout = 10000)
    // 一致性 Hash，相同参数的请求总是发到同一提供者，当某一台提供者挂时，原本发往该提供者的请求，基于虚拟节点，平摊到其它提供者。
//    @Reference(check = false, loadbalance = "consistenthash", timeout = 10000)
    //异步回调
    @Reference(check = false, async = true, timeout = 10000)
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

    /**
     * 异步调用
     * @return
     */
    @GetMapping("/sayHelloAsync")
    @ApiOperation(value = "异步调用测试")
    public String sayHelloAsync() throws ExecutionException, InterruptedException {
        // 调用直接返回CompletableFuture(委托师傅做蛋糕)
        System.out.println("当前主线程：" + Thread.currentThread().getName());
        System.out.println("委托师傅做蛋糕..." + Thread.currentThread().getName());
        CompletableFuture<String> future =  userService.syaHelloAsync();
        if (future == null){
            return "回调为空";
        }
        //这个方法只有等异步线程执行结束才会调用(做好了告诉我一声)
        future.whenComplete((v, t) -> {
            if (t != null) {
                t.printStackTrace();
            } else {
                System.out.println("师傅通知我做好蛋糕了，我现在开始过去拿...Response: " + v + Thread.currentThread().getName());
            }
        });
        System.out.println("我先去喝杯牛奶(做其他事)..." + Thread.currentThread().getName());
        //future.get()次方法阻塞获取结果
        return future.get();
    }

}

