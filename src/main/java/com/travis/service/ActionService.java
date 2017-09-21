package com.travis.service;

import com.travis.dto.ActionDto;

public interface ActionService {

    /**
     * 新增动作
     * @param dto
     * @return true:新增成功;false:新增失败
     */
    boolean add(ActionDto dto);

    /**
     * 删除动作
     * @param id
     * @return true:删除成功;false:删除失败
     */
    boolean remove(Long id);

    /**
     * 修改动作
     * @param dto
     * @return true:修改成功;false:修改失败
     */
    boolean modify(ActionDto dto);

    /**
     * 通过id获取动作
     * @param id
     * @return 动作DTO对象
     */
    ActionDto getById(Long id);
}
