package com.travis.dto;

import com.travis.constant.PageCodeEnum;

/**
 *  页面返回码
 */
public class PageCodeDto {

    private Integer code;
    private String msg;

    public PageCodeDto() {

    }

    public PageCodeDto(PageCodeEnum pageCodeEnum) {
        this.code = pageCodeEnum.getCode();
        this.msg = pageCodeEnum.getMsg();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
