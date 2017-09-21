package com.travis.dao;


import java.util.List;
import java.util.Map;

public interface ReportDao {

    /**
     * 按商户类别和时间段统计订单数
     * @return 订单数统计结果集，key值说明：
     *                categoryName 商户类别的中文名
     *                hour 小时数，如：01，表示凌晨1点至2点这个时间段
     *                count 订单数量
     */
    List<Map<String,String>> countOrder();



}
