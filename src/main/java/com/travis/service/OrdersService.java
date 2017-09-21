package com.travis.service;


import com.travis.dto.OrdersDto;

import java.util.List;

public interface OrdersService {
	
	/**
	 * 新增订单
	 * @param ordersDto
	 * @return 是否新增成功：true：新增成功，false：新增失败
	 */
	boolean add(OrdersDto ordersDto);
	
	/**
     * 根据主键获取订单的Dto对象
     * @param id 订单表主键值
     * @return 订单的Dto对象
     */
	OrdersDto getById(Long id);
	
	/**
	 * 根据会员ID获取该会员的全部订单dto列表
	 * @param memberId 会员ID
	 * @return 会员的订单dto列表
	 */
	List<OrdersDto> getListByMemberId(Long memberId);


	/**
	 * 根据会员电话号码查询订单
	 * @return
	 */
	List<OrdersDto> getListByMemberPhone(OrdersDto dto);


}