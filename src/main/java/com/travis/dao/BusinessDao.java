package com.travis.dao;

import com.travis.bean.Business;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BusinessDao {

    /**
     *  根据查询条件分页查询商户列表
     * @param business 查询条件
     * @return 商户列表
     */
    List<Business> selectByPage(Business business);

    /**
     *  根据主键查询商户
     * @param id 主键
     * @return 商户对象
     */
    Business selectById(Long id);

    /**
     * 新增
     * @param business 商户表对象
     * @return 影响行数
     */
    int insert(Business business);

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改商户
     * @param business
     * @return
     */
    int update(Business business);

    /**
     *  根据查询条件分页查询商户列表 :
     *  标题、副标题、描述三个过滤条件为模糊查询
     * @param business 查询条件
     * @return 商户列表
     */
    List<Business> selectLikeByPage(Business business);

    /**
     * 更新用户总星数量和总评论数
     * @param timestamp
     * @return 影响行数
     */
    int updateStarAndComment(Map<String,Date> timestamp);

    /**
     * 更新商户总价格和总销量
     * @param timestamp
     * @return
     */
    int updateNumberAndPrice(Map<String,Date> timestamp);
}
