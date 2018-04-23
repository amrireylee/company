var moduleCode = '02001';
var emId = 0;

function initFun() {
    if (secure.find) {
        //findDepartment();
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
 * 获取出勤列表
 */
function findListInfo() {
    $.post('mgr/employees/attendanceRecord/findAttendanceRecordList', {
        pageNum : page,
        id : $('.searchInput').val()
    }, function(data) {
        dialog.close();
        var tbody = $('tbody.tbody').empty();
        if (!$.isSuccess(data)) return;
        $.each(data.body.list, function(i, v) {
            $('<tr></tr>')
                .append($('<td></td>').append(v.id))
                .append($('<td></td>').append(v.emId))
                .append($('<td></td>').append(v.inDays+"天"))
                .append($('<td></td>').append(v.outDays+"天"))
                .append($('<td></td>').append(analyzeBtns(v)))
                .appendTo(tbody);
        });
    }, 'json');
    /*$.post('mgr/employees/attendanceRecord/findAttendanceRecordList', {
        pageNum : page,
        emId : $('.searchInput').val()
    }, function(data) {
        $.analysisPage(data.body.list);
    }, 'json');*/
}

/*
 * 分析操作按钮
 */
function analyzeBtns(v) {
    var btns = "";
    btns += secure.modify ? "<button type='button' class='btn btn-primary btn-xs' onclick='showModifyBox("+ v.id + ")'><span class='glyphicon glyphicon-pencil'></span>编辑</button>" : "";
    btns += secure.find ? "<button type='button' class='btn btn-success btn-xs' onclick='showRecord(" + v.emId + ")'><span class='glyphicon glyphicon-paperclip'></span>记录</button>" : "";
    btns += secure.del ? "<button type='button' class='btn btn-danger btn-xs' onclick='hintDelete(" + v.id + ")'><span class='glyphicon glyphicon-remove'></span>删除</button>" : "";
    return btns;
}

/*
 * 提示并确定删除出勤信息
 */
function hintDelete(id) {
    if (!id) return;
    BootstrapDialog.confirm("请确认是否要删除该出勤记录?<br />", function(result) {
        if (!result) return;
        dialog = BootstrapDialog.isSubmitted();
        $.post('mgr/employees/attendanceRecord/delete', {id : id}, function(data) {
            dialog.close();
            if (!$.isSuccess(data)) return;
            findListInfo();
            BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
        });
    });
}
/*
 * 显示出勤编辑窗口
 */
function showModifyBox(id) {
    $('.empty').removeClass('empty');
    if (!id) return;
    dialog = BootstrapDialog.loading();
    $('input.modifyId').val("");
    $('input.modifyInDays').val("");
    $('input.modifyOutDays').val("");
    $.getJSON('mgr/employees/attendanceRecord/findAttendanceRecordList', {pageNum : page,pageSize : page,id : id}, function(data) {
        dialog.close();
        if (!$.isSuccess(data)) return;
        $('input.modifyId').val(data.body.list[0].id);
        $('input.modifyInDays').val(data.body.list[0].inDays);
        $('input.modifyOutDays').val(data.body.list[0].outDays);
        $('button.modifyBtn').attr('onclick', 'modifyId(' + data.body.list[0].id + ')');
        $('div.modify-box').modal({
            closable : false,
            show : true
        });
    });
}
/*
 * 修改出勤信息
 */
var attendanceRecord = {};
function modifyId(id) {
    if (!id) return;
    $.isSubmit = true;
    attendanceRecord.id = id;
    attendanceRecord.inDays = $.verifyForm($('input.modifyInDays'), true);
    attendanceRecord.outDays = $.verifyForm($('input.modifyOutDays'), true);

    if (!$.isSubmit) return;
    dialog = BootstrapDialog.isSubmitted();
    $.ajax({
        url:'mgr/employees/attendanceRecord/saveRecord',
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
 * 显示出勤添加窗口
 */
function showAddBox() {
    $('.empty').removeClass('empty');
    $('input.addId').val('');
    $('input.addInDays').val('');
    $('input.addOutDays').val('');
    BootstrapDialog.showModel($('div.add-box'));
}
/*
 * 添加出勤信息
 */


function addAttendanceRecord() {
    $.isSubmit = true;
    attendanceRecord.id = $.verifyForm($('input.addId'), true);
    attendanceRecord.inDays = $.verifyForm($('input.addInDays'), true);
    attendanceRecord.outDays = $.verifyForm($('input.addOutDays'), false);
    if (!$.isSubmit) return;
    dialog = BootstrapDialog.isSubmitted();
    $.ajax({
        url:'mgr/employees/attendanceRecord/saveRecord',
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
        emId : attendanceRecord.id,
        inDays : attendanceRecord.inDays,
        outDays : attendanceRecord.outDays
    });
}

//拼装表单数据2
function buildReqData2(){
    return JSON.stringify({
        id : attendanceRecord.id,
        inDays : attendanceRecord.inDays,
        outDays : attendanceRecord.outDays
    });
}

/*
 * 显示缺勤记录列表记录
 */
var attendanceAbsent = {};
function showRecord(id) {
    if (!id) return;
    attendanceAbsent.emId = id;
    dialog = BootstrapDialog.loading();
    $.post('mgr/employees/attendanceAbsent/findAttendanceAbsentList', {emId : id},function(data) {
        dialog.close();
        $('button.add-empl-btn').removeClass('hide');
        $('table.table-record-list').removeClass('hide');
        $('div.record-modal-body').find('div.notdate').remove();
        if (!$.isSuccess(data)) return;
            /*if(data.body.state == 0 || (training.state == 1 && training.attend))
               $('button.add-empl-btn').removeClass('hide');
        */
            var tbody = $('tbody.record-list').empty();
            $.each(data.body.list, function(i,v){
                $('<tr></tr>')
                    .append($('<td></td>').append(v.id))
                    .append($('<td></td>').append(v.emId))
                    .append($('<td></td>').append(typeIdFun(v.typeId)))
                    .append($('<td></td>').append(v.outDays))
                    .append($('<td></td>').append(v.createTime))
                    .append($('<td></td>').append(analyzeApplyBtns(v)))
                    .appendTo(tbody);
            });
            BootstrapDialog.showModel($('div.record-box'));
        var body = $('div.record-modal-body');
        var button = "<button type='button' class='btn btn-success btn-xs' onclick='showAddEmplBox()'><span class='glyphicon glyphicon-plus'></span>点击这里添加缺勤</button>";
    }, 'json');
}

function typeIdFun(id) {
    if(id===1){
        return '正常';
    }else if (id===2){
        return '事假';
    }else if (id===3){
        return '病假';
    }else if (id===4){
        return '迟到';
    }else if (id===5){
        return '早退';
    }else if (id===6){
        return '旷工';
    }else if (id===7){
        return '出差';
    }
}
function closeAbsentListBox() {
    showRecord(attendanceAbsent.emId);
    BootstrapDialog.hideModel($('div.add-empl-box'));
}

function showAddAbsentBox() {
    if (!attendanceAbsent.emId) return;
    $('.empty').removeClass('empty');
    $('input.addAbsentId').val(attendanceAbsent.emId);
    $('select.participate').val('');
    BootstrapDialog.hideModel($('div.record-box'));
    BootstrapDialog.showModel($('div.add-empl-box'));
}

function addAttendanceAbsent() {
    $.isSubmit = true;
    attendanceAbsent.emId = $.verifyForm($('input.addAbsentId'), true);
    attendanceAbsent.typeId = $.verifyForm($('select.select-absent'), true);
    attendanceAbsent.outDays = $.verifyForm($('input.participate'), false);
    if (!$.isSubmit) return;
    dialog = BootstrapDialog.isSubmitted();
    $.ajax({
        url:'mgr/employees/attendanceAbsent/saveAbsent',
        dataType: "json",
        data:buildReqData3(),
        type:"post",
        contentType:"application/json;charset=UTF-8",
        async : false,
        success:function(data){
            dialog.close();
            if (!$.isSuccess(data)) return;
            BootstrapDialog.hideModel($('div.add-empl-box'));
            BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
            BootstrapDialog.showModel($('div.record-box'));
            showRecord(attendanceAbsent.emId);
        }
    })
}

//拼装表单数据3
function buildReqData3(){
    return JSON.stringify({
        emId : attendanceAbsent.emId,
        typeId : attendanceAbsent.typeId,
        outDays : attendanceAbsent.outDays
    });
}

/*
 * 渲染缺勤记录操作按钮
 */
function analyzeApplyBtns(v){
    var btns = "";
        btns += "<button type='button' class='btn btn-danger btn-xs' onclick='delEmployeesAbsent(" + v.id + ")'><span class='glyphicon glyphicon-minus'></span>删除</button>" ;

    return btns;
}

/*
 * 删除员工缺勤记录
 */
function delEmployeesAbsent(id){
    BootstrapDialog.confirm('请确认是否删除该员工的缺勤记录?', function(result){
        if(!result) return;
        dialog = BootstrapDialog.loading();
        $.post('./mgr/employees/attendanceAbsent/delete', {
            id : id
        }, function(data){
            dialog.close();
            if(!$.isSuccess(data)) return;
            BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
            showRecord(attendanceAbsent.id);
        });
    });
}