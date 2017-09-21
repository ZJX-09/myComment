package com.travis.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class OrderForBuyDto {
	
	// 商户主键
	private Long id;
	// 登录成功后的token
	private String token;
	// 消费人数前端默认为1
	private Integer num;
	// 消费金额
	private Double price;
	// 会员手机号
	private Long username;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Long getUsername() {
		return username;
	}
	public void setUsername(Long username) {
		this.username = username;
	}
	public Double getPrice() {
	    return price;
	}
	public void setPrice(Double price) {
	    this.price = price;
	}
}