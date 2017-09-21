package com.travis.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.travis.constant.ApiCodeEnum;

@JsonInclude(Include.NON_NULL)
public class ApiCodeDto {

    private Integer errno;

    private String msg;

    private String token;

    private String code;

    public ApiCodeDto() {
    }

    public ApiCodeDto(Integer errno, String msg) {
        this.errno = errno;
        this.msg = msg;
    }

    // 对枚举类型的api返回码进一步的封装
    public ApiCodeDto(ApiCodeEnum apiCodeEnum) {
        this.errno = apiCodeEnum.getErrno();
        this.msg = apiCodeEnum.getMsg();
    }

    public ApiCodeDto(ApiCodeEnum apiCodeEnum,String code) {
        this.errno = apiCodeEnum.getErrno();
        this.msg = apiCodeEnum.getMsg();
        this.code = code;
    }

    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
