package com.travis.controller.system;

import com.travis.bean.Dic;
import com.travis.constant.DicTypeConst;
import com.travis.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 权限管理控制层
 */

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private DicService dicService;

    @RequestMapping
    public String index(Model model){
        List<Dic> httpMethodList = dicService.getListByType(DicTypeConst.HTTP_METHOD);
        model.addAttribute("httpMethodList",httpMethodList);
        return "/system/auth";
    }

}
