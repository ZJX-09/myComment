package com.travis.dao;

import com.travis.bean.SysParam;

/**
 * 系统参数（同步任务系统参数）
 */
public interface SysParamDao {

    /**
     * 根据KEY获取对应的系统参数值
     * @param key
     * @return 系统参数值
     */
    SysParam selectByKey(String key);

    /**
     * 根据KEY修改对应的系统参数值
     * @param sysParam
     * @return 影响行数
     */
    int updateByKey(SysParam sysParam);

}
