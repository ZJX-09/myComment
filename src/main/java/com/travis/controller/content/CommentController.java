package com.travis.controller.content;

import com.travis.dto.CommentDto;
import com.travis.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping
    public String search(CommentDto dto, Model model){
        model.addAttribute("commentList",commentService.getListByConditioin(dto));
        model.addAttribute("searchParam", dto);
        return "/content/commentList";
    }

}
