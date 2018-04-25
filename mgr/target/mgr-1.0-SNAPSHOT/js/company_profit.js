var moduleCode = '07001';
var emId = 0;

function initFun() {
    if (secure.find) {
        findListInfo();
        dialog = BootstrapDialog.loading();
    }
    if (!secure.add) {
        $('button.add-btn').remove();
    }
    if (secure.add) {
        $('button.add-btn').removeClass('hide');
    }
}
/*
 * 获取资产列表
 */
function findListInfo() {
    $.post('mgr/employees/profit/findProfitList', {
        pageNum : page,
        id : $('.searchInput').val()
    }, function(data) {
        dialog.close();
        var tbody = $('tbody.tbody').empty();
        if (!$.isSuccess(data)) return;
        $.analysisPage(data.body);
        $.each(data.body.list, function(i, v) {
            $('<tr></tr>')
                .append($('<td></td>').append(v.id))
                .append($('<td></td>').append(v.principal+"元"))
                .append($('<td></td>').append(v.type===1 ? "收入" : "支出"))
                .append($('<td></td>').append(v.income+"元"))
                .append($('<td></td>').append(v.profit+"元"))
                .append($('<td></td>').append(v.remark))
                // .append($('<td></td>').append(analyzeBtns(v)))
                .appendTo(tbody);
        });
    }, 'json');
}

/*
 * 分析操作按钮
 */
function analyzeBtns(v) {
    var btns = "";
    if (secure.modify) {
        btns += v.notUseNum!==0 ? "<button type='button' class='btn btn-warning btn-xs' onclick='useThing("+ v.id + ")'><span class='glyphicon glyphicon-pencil'></span>使用</button>" : "";
        btns += v.useNum!==0 ? "<button type='button' class='btn btn-danger btn-xs' onclick='notUseThing("+ v.id + ")'><span class='glyphicon glyphicon-pencil'></span>返还</button>" : "";
    }
    btns += secure.modify ? "<button type='button' class='btn btn-primary btn-xs' onclick='showModifyBox("+ v.id + ")'><span class='glyphicon glyphicon-pencil'></span>编辑</button>" : "";
    btns += secure.del ? "<button type='button' class='btn btn-danger btn-xs' onclick='hintDelete(" + v.id + ")'><span class='glyphicon glyphicon-remove'></span>删除</button>" : "";
    return btns;
}


/*
 * 显示物品添加窗口
 */
function showAddBox() {
    $('.empty').removeClass('empty');
    $('input#addIncome').val('');
    $('input#addRemark').val('');
    BootstrapDialog.showModel($('div.add-box'));
}
/*
 * 添加物品信息
 */

var profit = {};
function addProfit() {
    $.isSubmit = true;
    profit.type = $.verifyForm($('select#addType'), true);
    profit.income = $.verifyForm($('input#addIncome'), false);
    profit.remark = $.verifyForm($('input#addRemark'), true);
    if (!$.isSubmit) return;
    dialog = BootstrapDialog.isSubmitted();
    $.ajax({
        url:'mgr/employees/profit/saveProfit',
        dataType: "json",
        data:buildReqData(),
        type:"post",
        contentType:"application/json;charset=UTF-8",
        async : false,
        success:function(data){
            dialog.close();
            if (!$.isSuccess(data)) return;
            BootstrapDialog.hideModel($('div.add-box'));
            BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
            findListInfo();
        }
    })

}

//拼装表单数据
function buildReqData(){
    return JSON.stringify({
        type : profit.type,
        income : profit.income,
        remark : profit.remark
    });
}
