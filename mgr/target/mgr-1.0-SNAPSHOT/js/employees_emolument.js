var moduleCode = '02002';
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
 * 获取薪资列表
 */
function findListInfo() {
    $.post('mgr/employees/salary/findSalaryList', {
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
                .append($('<td></td>').append(v.base+"元"))
                .append($('<td></td>').append(v.post+"元"))
                .append($('<td></td>').append(v.prix+"元"))
                .append($('<td></td>').append(v.sum+"元"))
                .append($('<td></td>').append(analyzeBtns(v)))
                .appendTo(tbody);
        });
    }, 'json');
    $.post('mgr/employees/salary/findSalaryList', {
        pageNum : page,
        id : $('.searchInput').val()
    }, function(data) {
        $.analysisPage(data.body.list);
    }, 'json');
}

/*
 * 分析操作按钮
 */
function analyzeBtns(v) {
    var btns = "";
    btns += secure.modify ? "<button type='button' class='btn btn-primary btn-xs' onclick='showModifyBox("+ v.id + ")'><span class='glyphicon glyphicon-pencil'></span>编辑</button>" : "";
    btns += secure.del ? "<button type='button' class='btn btn-danger btn-xs' onclick='hintDelete(" + v.id + ")'><span class='glyphicon glyphicon-remove'></span>删除</button>" : "";
    return btns;
}

/*
 * 提示并确定删除薪资信息
 */
function hintDelete(id) {
    if (!id) return;
    BootstrapDialog.confirm("请确认是否要删除该薪资记录?<br />", function(result) {
        if (!result) return;
        dialog = BootstrapDialog.isSubmitted();
        $.post('mgr/employees/salary/delete', {id : id}, function(data) {
            dialog.close();
            if (!$.isSuccess(data)) return;
            findListInfo();
            BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
        });
    });
}
/*
 * 显示薪资编辑窗口
 */
function showModifyBox(id) {
    $('.empty').removeClass('empty');
    if (!id) return;
    dialog = BootstrapDialog.loading();
    $('input.modifyId').val("");
    $('input.modifyBase').val("");
    $('input.modifyPost').val("");
    $('input.modifyPrix').val("");
    $.getJSON('mgr/employees/salary/findSalaryList', {pageNum : page,pageSize : page,id : id}, function(data) {
        dialog.close();
        if (!$.isSuccess(data)) return;
        $('input.modifyId').val(data.body.list[0].id);
        $('input.modifyBase').val(data.body.list[0].base);
        $('input.modifyPost').val(data.body.list[0].post);
        $('input.modifyPrix').val(data.body.list[0].prix);
        $('button.modifyBtn').attr('onclick', 'modifyId(' + data.body.list[0].id + ')');
        $('div.modify-box').modal({
            closable : false,
            show : true
        });
    });
}
/*
 * 修改薪资信息
 */
var salary = {};
function modifyId(id) {
    if (!id) return;
    $.isSubmit = true;
    salary.id = id;
    salary.base = $.verifyForm($('input.modifyBase'), true);
    salary.post = $.verifyForm($('input.modifyPost'), true);
    salary.prix = $.verifyForm($('input.modifyPrix'), true);

    if (!$.isSubmit) return;
    dialog = BootstrapDialog.isSubmitted();
    $.ajax({
        url:'mgr/employees/salary/saveSalary',
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
 * 显示薪资添加窗口
 */
function showAddBox() {
    $('.empty').removeClass('empty');
    $('input.addId').val('');
    $('input.addBase').val('');
    $('input.addPost').val('');
    $('input.addPrix').val('');
    BootstrapDialog.showModel($('div.add-box'));
}
/*
 * 添加薪资信息
 */


function addSalary() {
    $.isSubmit = true;
    salary.id = $.verifyForm($('input.addId'), true);
    salary.base = $.verifyForm($('input.addBase'), true);
    salary.post = $.verifyForm($('input.addPost'), true);
    salary.prix = $.verifyForm($('input.addPrix'), true);
    if (!$.isSubmit) return;
    dialog = BootstrapDialog.isSubmitted();
    $.ajax({
        url:'mgr/employees/salary/saveSalary',
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
        emId : salary.id,
        base : salary.base,
        post : salary.post,
        prix : salary.prix
    });
}

//拼装表单数据2
function buildReqData2(){
    return JSON.stringify({
        id : salary.id,
        base : salary.base,
        post : salary.post,
        prix : salary.prix
    });
}