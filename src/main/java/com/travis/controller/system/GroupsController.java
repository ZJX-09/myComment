package com.travis.controller.system;

import com.travis.constant.PageCodeEnum;
import com.travis.dto.GroupDto;
import com.travis.dto.PageCodeDto;
import com.travis.service.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户组相关
 */
@RestController
@RequestMapping("/groups")
public class GroupsController {

    private static final Logger log = LoggerFactory.getLogger(GroupsController.class);

    @Autowired
    private GroupService groupService;

    /**
     * 新增用户组
     */
    @RequestMapping(method = RequestMethod.POST)
    public PageCodeDto add(GroupDto groupDto) {
        PageCodeDto result;
        if(groupService.add(groupDto)) {
            result = new PageCodeDto(PageCodeEnum.ADD_SUCCESS);
        } else {
            result = new PageCodeDto(PageCodeEnum.GROUPNAME_EXISTS);
        }
        return result;
    }

    /**
     * 删除用户组
     */
    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public PageCodeDto modify(@PathVariable("id")Long id) {
        PageCodeDto result;
        if(groupService.remove(id)) {
            result = new PageCodeDto(PageCodeEnum.REMOVE_SUCCESS);
        } else {
            result = new PageCodeDto(PageCodeEnum.REMOVE_FAIL);
        }
        return result;
    }

    /**
     * 修改用户组
     */
    @RequestMapping(value="/{id}",method = RequestMethod.PUT)
    public PageCodeDto modify(GroupDto groupDto) {
        PageCodeDto result;
        if(groupService.modify(groupDto)) {
            result = new PageCodeDto(PageCodeEnum.MODIFY_SUCCESS);
        } else {
            result = new PageCodeDto(PageCodeEnum.GROUPNAME_EXISTS);
        }
        return result;
    }

    /**
     * 根据主键获取用户组dto
     */
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public GroupDto getGroupById(@PathVariable("id") Long id) {
        return groupService.getById(id);
    }

    /**
     * 获取用户组列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<GroupDto> getList() {
        return groupService.getList();
    }


   /* @RequestMapping(value = "/{id}/menus",method = RequestMethod.GET)
    public GroupDto getMenuList(@PathVariable("id") Long id){
        return groupService.getMenuList(id);
    }*/

    /**
     * 获取用户组对应可以访问的菜单和动作
     */
    @RequestMapping(value="/{id}/menus",method = RequestMethod.GET)
    public GroupDto getMenuList(@PathVariable("id")Long id) {
        return groupService.getByIdWithMenuAction(id);
    }

    /**
     * 为用户组分配可以访问的菜单
     */
    @RequestMapping(value = "/{id}/menus",method = RequestMethod.POST)
    public PageCodeDto assignMenu (GroupDto groupDto){
        PageCodeDto result;
        if(groupService.assignMenu(groupDto)) {
            result = new PageCodeDto(PageCodeEnum.ASSIGN_SUCCESS);
        } else {
            result = new PageCodeDto(PageCodeEnum.ASSIGN_FAIL);
        }
        return result;
    }
}
