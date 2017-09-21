package com.travis.dto.echarts;

import java.util.ArrayList;
import java.util.List;

/**
 * 与前端legend对象相互对应
 */
public class Legend {

    private List<String> data;

    public Legend(){
        data = new ArrayList<>();
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
