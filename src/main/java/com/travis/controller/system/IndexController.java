package com.travis.controller.system;

import com.travis.constant.PageCodeEnum;
import com.travis.constant.SessionKeyConst;
import com.travis.dto.PageCodeDto;
import com.travis.dto.UserDto;
import com.travis.dto.UserDtoForReset;
import com.travis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private UserService userService;

    /**
     * 登录成功后，后台管理首页
     */
    @RequestMapping
    public String init(){
        return "/system/index";
    }
    @ResponseBody
    @RequestMapping(value = "/resetPwd",method = RequestMethod.POST)
    public PageCodeDto resetPassword(UserDtoForReset dto){
        PageCodeDto result = null;
        // 验证原始密码
        if(userService.checkPwd(dto)){
            // 修改密码
            if(userService.resetPwd(dto)){
                result = new PageCodeDto(PageCodeEnum.MODIFY_SUCCESS);
            }else{
                result = new PageCodeDto(PageCodeEnum.MODIFY_FAIL);
            }
        }else{
            result = new PageCodeDto(PageCodeEnum.VALIDATE_PWD_FAIL);
        }
        return result;
    }
}
