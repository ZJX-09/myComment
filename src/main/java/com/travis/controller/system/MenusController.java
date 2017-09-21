package com.travis.controller.system;


import com.travis.constant.PageCodeEnum;
import com.travis.dao.ActionDao;
import com.travis.dto.MenuDto;
import com.travis.dto.MenuForMoveDto;
import com.travis.dto.MenuForZtreeDto;
import com.travis.dto.PageCodeDto;
import com.travis.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menus")
public class MenusController {

    @Autowired
    private MenuService menuService;


    /**
     * 新增菜单
     */
    @RequestMapping(method = RequestMethod.POST)
    public PageCodeDto add(MenuDto menuDto) {
        PageCodeDto result;
        if(menuService.add(menuDto)) {
            result = new PageCodeDto(PageCodeEnum.ADD_SUCCESS);
        } else {
            result = new PageCodeDto(PageCodeEnum.ADD_FAIL);
        }
        return result;
    }

    /**
     * 删除菜单
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public PageCodeDto remove(@PathVariable("id") Long id){
        PageCodeDto result;
        MenuDto menuDto = new MenuDto();
        menuDto.setParentId(id);
        List<MenuDto> list = menuService.getList(menuDto);
        //先判断是否有子节点
        if (list.size() > 0){
            result = new PageCodeDto(PageCodeEnum.SUB_MENU_EXISTS);
        }else{
            if (menuService.remove(id)){
                result = new PageCodeDto(PageCodeEnum.REMOVE_SUCCESS);
            }else {
                result = new PageCodeDto(PageCodeEnum.REMOVE_FAIL);
            }
        }
        return result;
    }

    /**
     * 修改菜单
     */
    @RequestMapping(value="/{id}",method = RequestMethod.PUT)
    public PageCodeDto modify(MenuDto menuDto) {
        PageCodeDto result;
        if(menuService.modify(menuDto)) {
            result = new PageCodeDto(PageCodeEnum.MODIFY_SUCCESS);
        } else {
            result = new PageCodeDto(PageCodeEnum.MODIFY_FAIL);
        }
        return result;
    }

    /**
     * 获取菜单列表
     */
     /*  @RequestMapping
    public List<MenuDto> getZtreeList(){
        return menuService.getZtreeList();
    }*/

    /**
     * 获取菜单列表
     */
    @RequestMapping
    public List<MenuForZtreeDto> getZtreeList(){
        return menuService.getZtreeList();
    }

    /**
     * 根据主键获取菜单dto
     */
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public MenuDto getById(@PathVariable("id") Long id) {
        return menuService.getById(id);
    }

    /**
     * 菜单排序
     */
    @RequestMapping(value = "/{dropNodeId}/{targetNodeId}/{moveType}",method = RequestMethod.PUT)
    public PageCodeDto order(MenuForMoveDto menuForMoveDto){
        PageCodeDto result;
        if (menuService.order(menuForMoveDto)){
            result = new PageCodeDto(PageCodeEnum.ORDER_SUCCESS);
        }else{
            result = new PageCodeDto(PageCodeEnum.ORDER_FAIL);
        }
        return result;
    }

}
