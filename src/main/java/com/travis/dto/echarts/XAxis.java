package com.travis.dto.echarts;

import java.util.ArrayList;
import java.util.List;

/**
 * 与前端的xAxis相互对应
 */
public class XAxis {

    private List<String> data;

    public XAxis(){
        data = new ArrayList<>();
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
