package com.travis.controller.report;

import com.travis.dto.echarts.Option;
import com.travis.service.OrderReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/orderReport")
public class OrderReportController {

    @Autowired
    private OrderReportService orderReportService;

    @RequestMapping
    public String index(){
        return "/report/orderCount";
    }

    @ResponseBody
    @RequestMapping(value="/count" , method = RequestMethod.GET)
    public Option count() {
        return orderReportService.count();
    }

}
