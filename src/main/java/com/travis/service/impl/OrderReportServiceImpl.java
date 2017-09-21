package com.travis.service.impl;

import com.travis.dao.ReportDao;
import com.travis.dto.echarts.Option;
import com.travis.dto.echarts.Serie;
import com.travis.service.OrderReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderReportServiceImpl implements OrderReportService {

    @Autowired
    private ReportDao reportDao;

    @Override
    public Option count() {
        Option option = new Option();
        //获取原料
        List<Map<String, String>> reportMaps = reportDao.countOrder();
        //重新组装格式化原料，为后面拼装series做准备
        // 类别+时间为KEY，数量为VALUE 由类别+时间可以确定线上的唯一一个点
        Map<String, Long> countMap = new HashMap<>();
        // 使用Set是为了legend的data不重复，TreeSet是有一定的顺序
        // 类别 legend
        Set<String> legendSet = new TreeSet<>();
        for (Map<String, String> map:reportMaps) {
            // 这步很重要 重新对数据库中的数据格式化一下，方便以后设置series中的data
            countMap.put(map.get("categoryName") + map.get("hour"),Long.valueOf(map.get("count")));
            legendSet.add(map.get("categoryName"));
        }
        //组装Option中的legend data
        option.getLegend().setData(new ArrayList<String>(legendSet));
        //组装Option中的xAxis data
        List<String> hourList = new ArrayList<>();
        for (int i = 0; i <= 23 ; i++) {
            hourList.add(String.format("%02d", i));
        }
        option.getxAxis().setData(hourList);
        //组装Option中的series name data
        for (String categoryName:option.getLegend().getData()) {
            Serie serie = new Serie();
            option.getSeries().add(serie);
            serie.setName(categoryName);
            serie.setType(Serie.TYPE_NAME);
            for (String hourString:hourList) {
                Long hour = countMap.get(categoryName + hourString) == null ? 0 : countMap.get(categoryName + hourString);
                serie.getData().add(hour);
            }
        }
        return option;
    }

}
