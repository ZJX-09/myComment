package com.travis.service;

import com.travis.bean.Dic;

import java.util.List;

/**
 * 数据字典服务提供列表
 */
public interface DicService {

    /**
     * 根据类型获取字典表列表
     * @param type 类型
     * @return 字典表列表
     */
     List<Dic> getListByType(String type);


}
