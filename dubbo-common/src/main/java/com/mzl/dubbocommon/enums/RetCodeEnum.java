package com.mzl.dubbocommon.enums;

import lombok.Getter;

/**
 * @EnumName :   RetCodeEnum
 * @Description: 返回结果状态码枚举
 * @Author: mzl
 * @CreateDate: 2022/6/10 10:48
 * @Version: 1.0
 */
@Getter
public enum RetCodeEnum {

    SUCCESS(200, "成功"),
    FAILURE(500, "失败"),
    FINGER_DOWNLOAD_FAIL(501, "指纹下载错误"),
    NULL_POINT_EXCEPTION(502, "空指针异常~"),
    SERVICE_DEGRADE(601, "系统繁忙，服务降级"),
    ;

    private Integer code;
    private String message;

    RetCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public static String getMessage(String name) {
        for (RetCodeEnum item : RetCodeEnum.values()) {
            if (item.name().equals(name)) {
                return item.message;
            }
        }
        return name;
    }

    public static Integer getCode(String name) {
        for (RetCodeEnum item : RetCodeEnum.values()) {
            if (item.name().equals(name)) {
                return item.code;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name();
    }

}
