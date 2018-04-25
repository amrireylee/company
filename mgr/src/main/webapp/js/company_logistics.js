var moduleCode = '05001';
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
 * 获取物品列表
 */
function findListInfo() {
    $.post('mgr/employees/logistics/findLogisticsList', {
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
                .append($('<td></td>').append(v.name))
                .append($('<td></td>').append(v.logisticsDesc))
                .append($('<td></td>').append(v.useNum))
                .append($('<td></td>').append(v.notUseNum))
                .append($('<td></td>').append(v.total))
                .append($('<td></td>').append(analyzeBtns(v)))
                .appendTo(tbody);
        });
    }, 'json');
    /*$.post('mgr/employees/remind/findRemindList', {
        pageNum : page,
        id : $('.searchInput').val()
    }, function(data) {
        $.analysisPage(data.body);
    }, 'json');*/
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
 * 物品使用
 */
function useThing(id) {
    if (!id) return;
    BootstrapDialog.confirm("请确认是否借出该物品!", function(result) {
        if (!result) return;
        dialog = BootstrapDialog.loading();
        $.post('./mgr/employees/logistics/use', {id : id}, function(data) {
            dialog.close();
            if (!$.isSuccess(data)) return;
            BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
            findListInfo();
        }, 'json');
    });
}
/*
 * 物品返还
 */
function notUseThing(id) {
    if (!id) return;
    BootstrapDialog.confirm("请确认是否返还该物品!", function(result) {
        if (!result) return;
        dialog = BootstrapDialog.loading();
        $.post('./mgr/employees/logistics/notUse',
            {id : id},
            function(data) {
            dialog.close();
            if (!$.isSuccess(data)) return;
            BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
            findListInfo();
        }, 'json');
    });
}
/*
 * 提示并确定删除物品信息
 */
function hintDelete(id) {
    if (!id) return;
    BootstrapDialog.confirm("请确认是否要删除该物品记录?<br />", function(result) {
        if (!result) return;
        dialog = BootstrapDialog.isSubmitted();
        $.post('mgr/employees/logistics/delete', {id : id}, function(data) {
            dialog.close();
            if (!$.isSuccess(data)) return;
            findListInfo();
            BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
        }, 'json');
    });
}
/*
 * 显示物品编辑窗口
 */
function showModifyBox(id) {
    $('.empty').removeClass('empty');
    if (!id) return;
    dialog = BootstrapDialog.loading();
    $('input#modifyName').val("");
    $('input#modifyDesc').val("");
    $('input#modifyUse').val("");
    $('input#modifyNotUse').val("");
    $('input#modifyTotal').val("");
    $.getJSON('mgr/employees/logistics/findLogisticsList', {pageNum : page,pageSize : page,id : id}, function(data) {
        dialog.close();
        if (!$.isSuccess(data)) return;
        $('input#modifyId').val(data.body.list[0].id);
        $('input#modifyName').val(data.body.list[0].name);
        $('input#modifyDesc').val(data.body.list[0].logisticsDesc);
        $('input#modifyUse').val(data.body.list[0].useNum);
        $('input#modifyNotUse').val(data.body.list[0].notUseNum);
        $('input#modifyTotal').val(data.body.list[0].total);
        $('button.modifyBtn').attr('onclick', 'modifyId(' + data.body.list[0].id + ')');
        $('div.modify-box').modal({
            closable : false,
            show : true
        });
    });
}
/*
 * 修改物品信息
 */
var logistics = {};
function modifyId(id) {
    if (!id) return;
    $.isSubmit = true;
    logistics.id = id;
    logistics.name = $.verifyForm($('input#modifyName'), true);
    logistics.desc = $.verifyForm($('input#modifyDesc'), false);
    logistics.use = $.verifyForm($('input#modifyUse'), true);
    logistics.notUse = $.verifyForm($('input#modifyNotUse'), true);
    logistics.total = $.verifyForm($('input#modifyTotal'), true);

    if (!$.isSubmit) return;
    dialog = BootstrapDialog.isSubmitted();
    $.ajax({
        url:'mgr/employees/logistics/saveLogistics',
        dataType: "json",
        data:buildReqData2(),
        type:"post",
        contentType:"application/json;charset=UTF-8",
        async : false,
        success:function(data){
            dialog.close();
            if (!$.isSuccess(data)) return;
            BootstrapDialog.hideModel($('div.modify-box'));
            BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
            findListInfo();
        }
    })
}
/*
 * 显示物品添加窗口
 */
function showAddBox() {
    $('.empty').removeClass('empty');
    $('input#addName').val('');
    $('input#addDesc').val('');
    $('input#addTotal').val('');
    BootstrapDialog.showModel($('div.add-box'));
}
/*
 * 添加物品信息
 */


function addLogistics() {
    $.isSubmit = true;
    logistics.name = $.verifyForm($('input#addName'), true);
    logistics.desc = $.verifyForm($('input#addDesc'), false);
    logistics.total = $.verifyForm($('input#addTotal'), true);
    if (!$.isSubmit) return;
    dialog = BootstrapDialog.isSubmitted();
    $.ajax({
        url:'mgr/employees/logistics/saveLogistics',
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
        name : logistics.name,
        logisticsDesc : logistics.desc,
        total : logistics.total
    });
}

//拼装表单数据2
function buildReqData2(){
    return JSON.stringify({
        id : logistics.id,
        name : logistics.name,
        logisticsDesc : logistics.desc,
        useNum : logistics.use,
        notUseNum : logistics.notUse,
        total : logistics.total
    });
}