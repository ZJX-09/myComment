package com.travis.service;


import com.travis.dto.AdDto;

import java.util.List;

public interface AdService {

    /**
     * 新增广告
     * @param adDto
     * @return 是否新增成功：true-成功;fale-失败
     */
    boolean add(AdDto adDto);

    /**
     * 分页搜索广告列表
     *
     * @param adDto 查询条件(包含分页对象)
     * @return 广告列表
     */
    List<AdDto> searchByPage(AdDto adDto);

    /**
     * 删除广告
     * @param id
     * @return true:删除成功;false:删除失败
     */
    boolean remove(Long id);

    /**
     * 根据主键获取广告的Dto对象
     * @param id 广告表主键值
     * @return adDto对象
     */
    AdDto getById(Long id);

    /**
     * 修改广告
     * @param adDto
     * @return 是否修改成功：true-成功;fale-失败
     */
    boolean modify(AdDto adDto);

}
