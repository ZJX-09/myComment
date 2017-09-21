package com.travis.dao;


import com.travis.bean.Action;

public interface ActionDao {

    /**
     * 根据菜单ID删除动作
     * @param menuId 菜单ID
     * @return 影响行数
     */
    int deleteByMenuId(Long menuId);

    /**
     * 根据主键删除动作
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 新增
     * @param action
     * @return 影响行数
     */
    int insert(Action action);

    /**
     * 修改
     * @param action
     * @return 影响行数
     */
    int update(Action action);

    /**
     * 根据主键获取动作实体
     * @param id 主键
     * @return 动作实体
     */
    Action selectById(Long id);
}
