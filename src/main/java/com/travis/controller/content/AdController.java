package com.travis.controller.content;

import com.travis.constant.PageCodeEnum;
import com.travis.dto.AdDto;
import com.travis.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/ad")
public class AdController {

    @Autowired
    private AdService adService;

    /**
     * 广告管理页初始化(点广告管理菜单进入的第一个页面)
     */
    @RequestMapping
    public String init(Model model, HttpServletRequest request,AdDto adDto){
        //AdDto adDto = new AdDto();
        model.addAttribute("list", adService.searchByPage(adDto));
        model.addAttribute("searchParam", adDto);
        return "/content/adList";
    }

    /**
     * 查询
     */
    @RequestMapping("/search")
    public String search(Model model, AdDto adDto) { //adDto会传入拦截器中然后setTotalNumber，然后就有page的信息了
        model.addAttribute("list", adService.searchByPage(adDto));//列表中的adDto中的page没什么作用
        model.addAttribute("searchParam", adDto);
        return "/content/adList";
    }


    /**
     * 新增页初始化
     */
    @RequestMapping("/addInit")
    public String addInit(){
        return "/content/adAdd";
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    public String add(AdDto adDto,Model model){

        if (adService.add(adDto)){
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.ADD_SUCCESS);
        }else {
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.ADD_FAIL);
        }

        return "/content/adAdd";

    }

    /**
     * 删除
     */
    @RequestMapping("/remove")
    public String remove(@RequestParam("id") Long id, Model model) {
        if(adService.remove(id)) {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.REMOVE_SUCCESS);
        } else {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.REMOVE_FAIL);
        }
        return "forward:/ad";
    }

    /**
     * 修改页初始化
     */
    @RequestMapping("/modifyInit")
    public String modifyInit(Model model, @RequestParam("id") Long id) {
        model.addAttribute("modifyObj", adService.getById(id));
        return "/content/adModify";
    }

    /**
     * 修改
     */
    @RequestMapping("/modify")
    public String modify(Model model, AdDto adDto) {
        model.addAttribute("modifyObj", adDto);
        if (adService.modify(adDto)) {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.MODIFY_SUCCESS);
        } else {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.MODIFY_FAIL);
        }
        return "/content/adModify";
    }



}
