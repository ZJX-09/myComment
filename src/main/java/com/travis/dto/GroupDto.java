package com.travis.dto;

import com.travis.bean.Group;

import java.util.List;


public class GroupDto extends Group {

    private Integer pId;

    private List<MenuDto> menuDtoList;

    private List<ActionDto> actionDtoList;

    /*接收请求中的菜单id列表*/
    private List<Long> menuIdList;

    private List<Long> actionIdList;

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public List<MenuDto> getMenuDtoList() {
        return menuDtoList;
    }

    public void setMenuDtoList(List<MenuDto> menuDtoList) {
        this.menuDtoList = menuDtoList;
    }

    public List<Long> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<Long> menuIdList) {
        this.menuIdList = menuIdList;
    }

    public List<ActionDto> getActionDtoList() {
        return actionDtoList;
    }

    public void setActionDtoList(List<ActionDto> actionDtoList) {
        this.actionDtoList = actionDtoList;
    }

    public List<Long> getActionIdList() {
        return actionIdList;
    }

    public void setActionIdList(List<Long> actionIdList) {
        this.actionIdList = actionIdList;
    }
}
