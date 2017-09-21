package com.travis.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.Date;

@JsonInclude(Include.NON_NULL)
public class Comment extends BaseBean {
	
	private Long id;
	private String comment;
	private Integer star;
	private Long ordersId;
	private Date createTime;
	private Orders orders;
	
	public Long getId() {
	    return id;
	}
	public void setId(Long id) {
	    this.id = id;
	}
	public String getComment() {
	    return comment;
	}
	public void setComment(String comment) {
	    this.comment = comment;
	}
	public Integer getStar() {
	    return star;
	}
	public void setStar(Integer star) {
	    this.star = star;
	}
	public Long getOrdersId() {
	    return ordersId;
	}
	public void setOrdersId(Long ordersId) {
	    this.ordersId = ordersId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Orders getOrders() {
		return orders;
	}
	public void setOrders(Orders orders) {
		this.orders = orders;
	}
}
