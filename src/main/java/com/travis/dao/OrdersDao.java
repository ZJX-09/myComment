package com.travis.dao;


import com.travis.bean.Orders;
import java.util.List;

public interface OrdersDao {

    /**
     * 新增
     * @param orders 订单表对象
     * @return 影响行数
     */
    int insert(Orders orders);

    /**
     * 根据主键查询订单表对象
     * @param id 主键值
     * @return 订单表对象
     */
    Orders selectById(Long id);

    /**
     * 修改
     * @param orders 订单表对象
     * @return 影响行数
     */
    int update(Orders orders);

    /**
     * 根据条件查询订单列表
     * @param orders 查询条件
     * @return 订单列表
     */
    List<Orders> select(Orders orders);

    /**
     * 根据会员手机号码进行订单的查询
     * @param orders
     * @return
     */
    List<Orders> selectByPage(Orders orders);

}
