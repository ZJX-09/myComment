package com.travis.dto.echarts;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
/**
 * 与前端的option相对应
 */
@JsonInclude(Include.NON_NULL)
public class Option {

    private Legend legend;
    private XAxis xAxis;
    private List<Serie> series;

    public Option(){
        legend = new Legend();
        xAxis = new XAxis();
        series = new ArrayList<>();
    }

    public Legend getLegend() {
        return legend;
    }

    public void setLegend(Legend legend) {
        this.legend = legend;
    }

    public XAxis getxAxis() {
        return xAxis;
    }

    public void setxAxis(XAxis xAxis) {
        this.xAxis = xAxis;
    }

    public List<Serie> getSeries() {
        return series;
    }

    public void setSeries(List<Serie> series) {
        this.series = series;
    }
}
