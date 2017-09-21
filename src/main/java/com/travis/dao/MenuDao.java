package com.travis.dao;

import com.travis.bean.Menu;

import java.util.List;

public interface MenuDao {

    /**
     * 新增
     * @param menu
     * @return 影响行数
     */
    int insert(Menu menu);

    /**
     * 根据主键删除
     * @param id 主键
     * @return 影响行数
     */
    int delete(Long id);

    /**
     * 修改
     * @param menu
     * @return 影响行数
     */
    int update(Menu menu);

    /**
     * 修改指定菜单下所有子菜单的排序数字，修改为原排序数字+1
     * @param parentId 指定的菜单主键
     * @return 影响行数
     */
    int updateOrderNumByParentId(Long parentId);

    /**
     * 修改排序在指定菜单后面的兄弟节点(包括指定菜单本身)的排序数字，修改为原排序数字+1
     * @param menu
     * @return 影响行数
     */
    int updateOrderNumByMenuInclude(Menu menu);

    /**
     * 修改排序在指定菜单后面的兄弟节点(不包括指定菜单本身)的排序数字，修改为原排序数字+1
     * @param menu 指定菜单的Menu Bean
     * @return 影响行数
     */
    int updateOrderNumByMenu(Menu menu);

    /**
     * 根据主键获取菜单实体
     * @param id 主键
     * @return 菜单实体
     */
    Menu selectById(Long id);

    /**
     * 根据查询条件查询菜单列表（仅本表单表查询）
     * @param menu 查询条件
     * @return 菜单列表
     */
    List<Menu> select(Menu menu);

    List<Menu> selectZTree(Menu menu);

    /**
     * 根据查询条件查询菜单列表（关联动作表，结果集里包含了动作列表）
     * @param menu
     * @return 菜单列表
     */
    List<Menu> selectWithAction(Menu menu);
}
