package com.mzl.dubboconsumer.exception;

import com.mzl.dubbocommon.enums.RetCodeEnum;
import lombok.Data;

/**
 * @ClassName :   CustomException
 * @Description: 用户自定义异常
 * @Author: mzl
 * @CreateDate: 2022/6/13 13:32
 * @Version: 1.0
 */
@Data
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 4564124491192825748L;

    //错误码
    private int code;

    public CustomException() {
        super();
    }

    /**
     * 定义返回信息
     * @param message
     */
    public CustomException(String message) {
        super(message);
    }

    /**
     * 定义状态码+返回信息
     * @param code
     * @param message
     */
    public CustomException(int code, String message) {
        super(message); //把自定义的message传递个异常父类
        this.setCode(code);
    }

    /**
     * 使用枚举返回码
     * @param retCodeEnum
     */
    public CustomException(RetCodeEnum retCodeEnum) {
        super(retCodeEnum.getMessage()); //把自定义的message传递个异常父类
        this.setCode(retCodeEnum.getCode());
    }

    @Override
    public String toString() {
        return "Exception{" + "code=" + code + ", message=" + this.getMessage() + '}';
    }

}
