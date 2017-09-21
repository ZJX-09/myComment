$(function() {
	var myChart = echarts.init(document.getElementById('report'));
	common.ajax({
		url : $('#basePath').val() + '/orderReport/count',
		success : function(data) {
			var option = {
			    title: {
			        text: '商户类别订单数'
			    },
			    tooltip: {
			        trigger: 'axis'
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    toolbox: {
			        feature: {
			            saveAsImage: {}
			        }
			    },
			    xAxis: {
			        type: 'category',
			        boundaryGap: false
			    },
			    yAxis: {
			        type: 'value'
			    }
			};
			// 深度扩展
			$.extend(true,option,data);
			myChart.setOption(option);
		},
		type : 'GET'
	});
});