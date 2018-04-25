var moduleCode = '07002';
var emId = 0;

function initFun() {
    if (secure.find) {
        dialog = BootstrapDialog.loading();
        dialog.close();
    }
    if (!secure.add) {
        $('button.add-btn').remove();
    }
    if (secure.add) {
        $('button.add-btn').removeClass('hide');
    }
}

// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));


// 指定图表的配置项和数据
myChart.setOption(
    {
        tooltip : {
            trigger: 'axis'
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        legend: {
            data:['收入','支出','利润']
        },
        xAxis : [
            {
                type : 'category',
                data : []
            }
        ],
        yAxis : [
            {
                type : 'value',
                position: 'right',
                axisLabel : {
                    formatter: '{value} 元'
                },
                min : '0'

            }
        ],
        series : [

            {
                name:'收入',
                type:'bar',
                data:[]
            },
            {
                name:'支出',
                type:'bar',
                data:[]
            },
            {
                name:'利润',
                type:'line',
                data:[]
            }
        ]
    }
);
myChart.showLoading();

var incomes = [];
var expenses = [];
var profits = [];
var timestamps = [];

$.ajax({
    type : "post",
    async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
    url : "mgr/employees/profit/findProfitList",    //请求发送到TestServlet处
    data : {pageNum : 1,pageSize : 10},
    dataType : "json",        //返回数据形式为json
    success : function(data) {
        //请求成功时执行该函数内容，result即为服务器返回的json对象
        if (data) {
            $.each(data.body.list, function(i, v) {
                timestamps.push(v.timestamp);
                incomes.push(v.allIncome);
                expenses.push(v.allExpense);
                profits.push(v.profit);
            });
            myChart.hideLoading();    //隐藏加载动画
            myChart.setOption({        //加载数据图表
                xAxis : [
                    {
                        type : 'category',
                        data : timestamps
                    }
                ],
                series : [

                    {
                        name:'收入',
                        type:'bar',
                        data:incomes
                    },
                    {
                        name:'支出',
                        type:'bar',
                        data:expenses
                    },
                    {
                        name:'利润',
                        type:'line',
                        data:profits
                    }
                ]
            });

        }

    },
    error : function(errorMsg) {
        //请求失败时执行该函数
        alert("图表请求数据失败!");
        myChart.hideLoading();
    }
});
