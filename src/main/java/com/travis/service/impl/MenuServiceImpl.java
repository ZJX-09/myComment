package com.travis.service.impl;

import com.travis.bean.Action;
import com.travis.bean.Menu;
import com.travis.dao.ActionDao;
import com.travis.dao.MenuDao;
import com.travis.dto.MenuDto;
import com.travis.dto.MenuForMoveDto;
import com.travis.dto.MenuForZtreeDto;
import com.travis.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private ActionDao actionDao;


    @Override
    public boolean add(MenuDto menuDto) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuDto,menu);
        return menuDao.insert(menu) == 1;
    }

    @Override
    @Transactional
    public boolean remove(Long id) {

        actionDao.deleteByMenuId(id);
        return menuDao.delete(id) == 1;

    }

    @Override
    public boolean modify(MenuDto menuDto) {

        Menu menu = new Menu();
        BeanUtils.copyProperties(menuDto,menu);
        return menuDao.update(menu) == 1;

    }

    @Override
    public MenuDto getById(Long id) {

        MenuDto result = new MenuDto();
        Menu menu = menuDao.selectById(id);
        BeanUtils.copyProperties(menu, result);
        return result;

    }

   /* @Override
    public List<MenuDto> getZtreeList() {
        List<MenuDto> result = new ArrayList<>();
        Menu condition = new Menu();
        List<Menu> menuList = menuDao.selectZTree(condition);
        for (Menu menu : menuList) {
            MenuDto menuDtoTemp = new MenuDto();
            result.add(menuDtoTemp);
            BeanUtils.copyProperties(menu,menuDtoTemp);
        }
        return result;
    }*/

    @Override
    public List<MenuDto> getList(MenuDto menuDto) {

        List<MenuDto> result = new ArrayList<>();
        Menu menuForSelect = new Menu();
        BeanUtils.copyProperties(menuDto, menuForSelect);
        List<Menu> menuList = menuDao.select(menuForSelect);
        for(Menu menu : menuList) {
            MenuDto menuDtoTemp = new MenuDto();
            result.add(menuDtoTemp);
            BeanUtils.copyProperties(menu, menuDtoTemp);
        }
        return result;

    }

    @Override
    public List<MenuForZtreeDto> getZtreeList() {
        List<MenuForZtreeDto> result = new ArrayList<>();
        Menu menuForSelect = new Menu();
        List<Menu> menuList = menuDao.selectWithAction(menuForSelect);

        for (Menu menu:menuList) {
            MenuForZtreeDto menuForZtreeDto = new MenuForZtreeDto();
            result.add(menuForZtreeDto);
            BeanUtils.copyProperties(menu,menuForZtreeDto);
            menuForZtreeDto.setOpen(false);
            menuForZtreeDto.setComboId(MenuForZtreeDto.PREFIX_MENU + menu.getId());
            menuForZtreeDto.setComboParentId(MenuForZtreeDto.PREFIX_MENU + menu.getParentId());
            List<Action> actionList = menu.getActionList();
            for (Action action:actionList) {
                menuForZtreeDto = new MenuForZtreeDto();
                result.add(menuForZtreeDto);
                menuForZtreeDto.setId(action.getId());
                menuForZtreeDto.setParentId(action.getMenuId());
                menuForZtreeDto.setName(action.getName());
                menuForZtreeDto.setOpen(false);
                menuForZtreeDto.setComboId(MenuForZtreeDto.PREFIX_ACTION + action.getId());
                menuForZtreeDto.setComboParentId(MenuForZtreeDto.PREFIX_MENU + action.getMenuId());
            }
        }
        return result;
    }


    @Override
    @Transactional
    public boolean order(MenuForMoveDto menuForMoveDto) {

        // 如果移动到目标节点，成为目标节点的子节点
        if (MenuForMoveDto.MOVE_TYPE_INNER.equals(menuForMoveDto.getMoveType())){
            // 先将目标节点下所有子节点排序数字+1（让出最前面的位置）
            menuDao.updateOrderNumByParentId(menuForMoveDto.getTargetNodeId());
            // 将移动的节点排序数字改为1，成为目标节点下最前面的子节点
            // 无论是多么复杂的逻辑，这就是最终的目的，修改菜单表中的parent_id和order_num
            Menu menuForUpdate = new Menu();
            menuForUpdate.setId(menuForMoveDto.getDropNodeId());
            menuForUpdate.setParentId(menuForMoveDto.getTargetNodeId());
            menuForUpdate.setOrderNum(1);
            menuDao.update(menuForUpdate);
        }else {
            // 获取目标节点的排序数字
            Menu targetMenu = menuDao.selectById(menuForMoveDto.getTargetNodeId());
            if (targetMenu != null){
                // 如果移动到目标节点的前一个节点
                if (MenuForMoveDto.MOVE_TYPE_PREV.equals(menuForMoveDto.getMoveType())){
                    // 将目标节点和目标节点后面的兄弟节点的排序数字加1
                    // （留出一个空位，也就是原本目标节点的位置）
                    Menu menuForUpdateOrder = new Menu();
                    menuForUpdateOrder.setParentId(targetMenu.getParentId());
                    menuForUpdateOrder.setOrderNum(targetMenu.getOrderNum());
                    menuDao.updateOrderNumByMenuInclude(menuForUpdateOrder);
                    // 将移动的节点的排序数字更新为目标节点的原排序数字（排序到目标节点的前一个节点位置）
                    Menu menuForUpdate = new Menu();
                    menuForUpdate.setId(menuForMoveDto.getDropNodeId());
                    menuForUpdate.setParentId(targetMenu.getParentId());
                    menuForUpdate.setOrderNum(targetMenu.getOrderNum());
                    menuDao.update(menuForUpdate);
                }else if (MenuForMoveDto.MOVE_TYPE_NEXT.equals(menuForMoveDto.getMoveType())){
                    // 如果移动到目标节点的后一个节点
                    // 将目标节点后面的兄弟节点的排序数字加1（留出一个空位，也就是原本目标节点后面一个节点的位置）
                    Menu menuForUpdateOrder = new Menu();
                    menuForUpdateOrder.setParentId(targetMenu.getParentId());
                    menuForUpdateOrder.setOrderNum(targetMenu.getOrderNum());
                    menuDao.updateOrderNumByMenu(menuForUpdateOrder);
                    // 将移动的节点的排序数字更新为目标节点的原排序数字加1（排到目标节点的后一个节点位置）
                    Menu menuForUpdate = new Menu();
                    menuForUpdate.setId(menuForMoveDto.getDropNodeId());
                    menuForUpdate.setParentId(targetMenu.getParentId());
                    menuForUpdate.setOrderNum(targetMenu.getOrderNum() + 1);
                    menuDao.update(menuForUpdate);
                }else{
                    // 移动方式不可识别
                    return false;
                }
            }else {
                // 目标节点已不存在
                return false;
            }
        }
        return true;
    }
}
