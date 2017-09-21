package com.travis.service.impl;

import com.travis.bean.Dic;
import com.travis.dao.DicDao;
import com.travis.service.DicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class DicServiceImpl implements DicService {

    @Resource
    private DicDao dicDao;

    @Override
    public List<Dic> getListByType(String type) {

        Dic dic = new Dic();
        dic.setType(type);
        return dicDao.select(dic);

    }
}
