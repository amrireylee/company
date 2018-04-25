var moduleCode = '06001';
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
 * 获取事程列表
 */
function findListInfo() {
    $.post('mgr/employees/remind/findRemindList', {
        pageNum : page,
        title : $('.searchInput').val()
    }, function(data) {
        dialog.close();
        var tbody = $('tbody.tbody').empty();
        if (!$.isSuccess(data)) return;
        $.analysisPage(data.body);
        $.each(data.body.list, function(i, v) {
            $('<tr></tr>')
                .append($('<td></td>').append(v.id))
                .append($('<td></td>').append(v.title))
                .append($('<td></td>').append(v.startTime))
                .append($('<td></td>').append(v.endTime))
                .append($('<td></td>').append(v.area))
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
    btns += secure.find ? "<button type='button' class='btn btn-info btn-xs' onclick='showDetails(" + v.id + ")'><span class='glyphicon glyphicon-menu-left'></span>详情</button>" : "";
    btns += secure.modify ? "<button type='button' class='btn btn-primary btn-xs' onclick='showModifyBox("+ v.id + ")'><span class='glyphicon glyphicon-pencil'></span>编辑</button>" : "";
    btns += secure.del ? "<button type='button' class='btn btn-danger btn-xs' onclick='hintDelete(" + v.id + ")'><span class='glyphicon glyphicon-remove'></span>删除</button>" : "";
    return btns;
}

/*
 * 提示并确定删除事程信息
 */
function hintDelete(id) {
    if (!id) return;
    BootstrapDialog.confirm("请确认是否要删除该事程记录?<br />", function(result) {
        if (!result) return;
        dialog = BootstrapDialog.isSubmitted();
        $.post('mgr/employees/remind/delete', {id : id}, function(data) {
            dialog.close();
            if (!$.isSuccess(data)) return;
            BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
            findListInfo();
        }, 'json');
    });
}
/*
 * 显示事程编辑窗口
 */
function showModifyBox(id) {
    $('.empty').removeClass('empty');
    if (!id) return;
    dialog = BootstrapDialog.loading();
    $('input.modifyId').val("");
    $('input.modifyTitle').val("");
    $('input.modifyArea').val("");
    $('input.modifyStart').val("");
    $('input.modifyEnd').val("");
    $('input.modifyNote').val("");
    $.getJSON('mgr/employees/remind/findRemindList', {pageNum : page,pageSize : page,id : id}, function(data) {
        dialog.close();
        if (!$.isSuccess(data)) return;
        $('input.modifyId').val(data.body.list[0].id);
        $('input.modifyTitle').val(data.body.list[0].title);
        $('input.modifyArea').val(data.body.list[0].area);
        $('input.modifyStart').val(data.body.list[0].startTime);
        $('input.modifyEnd').val(data.body.list[0].endTime);
        $('input.modifyNote').val(data.body.list[0].note);
        $('button.modifyBtn').attr('onclick', 'modifyId(' + data.body.list[0].id + ')');
        $('div.modify-box').modal({
            closable : false,
            show : true
        });
    });
}
/*
 * 修改事程信息
 */
var remind = {};
function modifyId(id) {
    if (!id) return;
    $.isSubmit = true;
    remind.id = id;
    remind.title = $.verifyForm($('input#modifyTitle'), true);
    remind.area = $.verifyForm($('input#modifyArea'), true);
    remind.startTime = $.verifyForm($('input#modifyStart'), true);
    remind.endTime = $.verifyForm($('input#modifyEnd'), true);
    remind.note = $.verifyForm($('input#modifyNote'), true);

    if (!$.isSubmit) return;
    dialog = BootstrapDialog.isSubmitted();
    $.ajax({
        url:'mgr/employees/remind/saveRemind',
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
 * 显示事程添加窗口
 */
function showAddBox() {
    $('.empty').removeClass('empty');
    $('input#addTitle').val('');
    $('input#addArea').val('');
    $('input#addStart').val('');
    $('input#addEnd').val('');
    $('input#addNote').val('');
    BootstrapDialog.showModel($('div.add-box'));
}
/*
 * 添加事程信息
 */


function addRemind() {
    $.isSubmit = true;
    remind.title = $.verifyForm($('input#addTitle'), true);
    remind.area = $.verifyForm($('input#addArea'), true);
    remind.startTime = $.verifyForm($('input#addStart'), true);
    remind.endTime = $.verifyForm($('input#addEnd'), true);
    remind.note = $.verifyForm($('input#addNote'), true);
    if (!$.isSubmit) return;
    dialog = BootstrapDialog.isSubmitted();
    $.ajax({
        url:'mgr/employees/remind/saveRemind',
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
        title : remind.title,
        note : remind.note,
        startTime : remind.startTime,
        endTime : remind.endTime,
        area : remind.area
    });
}

//拼装表单数据2
function buildReqData2(){
    return JSON.stringify({
        id : remind.id,
        title : remind.title,
        note : remind.note,
        startTime : remind.startTime,
        endTime : remind.endTime,
        area : remind.area
    });
}

/*
 * 显示事程详情
 */
function showDetails(id) {
    if (!id) return;
    dialog = BootstrapDialog.loading();
    $.post('mgr/employees/remind/findRemindList', {
        pageNum : page,
        id : id
    }, function(data) {
        dialog.close();
        if (!$.isSuccess(data)) return;
        $('td.reminditem-id').text(data.body.list[0].id);
        $('td.reminditem-title').text(data.body.list[0].title);
        $('td.reminditem-area').text(data.body.list[0].area);
        $('td.reminditem-start').text(data.body.list[0].startTime);
        $('td.reminditem-end').text(data.body.list[0].endTime);
        $('td.reminditem-description').text(data.body.list[0].note);
        BootstrapDialog.showModel($('div.details-box'));
    }, 'json');
}