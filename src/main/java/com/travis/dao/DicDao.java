package com.travis.dao;

import com.travis.bean.Dic;

import java.util.List;

public interface DicDao {
    List<Dic> select(Dic dic);
}
