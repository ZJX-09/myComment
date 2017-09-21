package com.travis.dao;


import com.travis.bean.Ad;

import java.util.List;

public interface AdDao {

    /**
     * 新增
     * @param ad 广告表对象
     * @return 影响行数
     */
    int insert(Ad ad);

    /**
     * 根据查询条件分页查询
     * @param ad 查询条件：包括广告表的查询字段和分页信息
     * @return 广告列表
     */
    List<Ad> selectByPage(Ad ad);


    /**
     * 根据主键查询广告对象
     * @param id 主键值
     * @return 广告对象
     */
    Ad selectById(Long id);

    /**
     * 根据主键删除
     * @param id 主键
     * @return 影响行数
     */
    int delete(Long id);

    /**
     * 根据主键修改
     * @param ad 待修改的广告对象
     * @return 影响行数
     */
    int update(Ad ad);

}
