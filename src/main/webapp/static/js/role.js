$(function () {
    $("select").combobox({
        panelHeight:50,
        editable:false
    });
    initDatagride();
});

function searchRole() {
    var data = {
        roleName:$("#roleName").textbox("getValue"),
        roleCode:$("#roleCode").textbox("getValue"),
        valid:$("#valid").combobox("getValue")
    };
    $("#role-datagride").datagrid('load',data);
    console.log(data);
}

function addRole() {
    $("#role-window").window({
        modal: true,//灰色隔层
        closed: true,//是否可关闭
        width:300,
        height:340,
        iconCls: 'icon-edit',
        collapsible: false,//
        minimizable: false,
        maximizable: false,
        cloable:true
    }).window("open");
}

function submitForm() {
    var roleForm = $("#roleForm").form().serialize();
    console.log(roleForm);
    $.ajax({
        url:path+'/role/addRole',
        type:'post',
        data:roleForm,
        success:function (res) {
            if(res.msg){
                //添加成功
                //成功修改
                $("#role-window").window("close");
                $("#role-treegrid").treegrid("reload");
            }
        }
    })
}


function initDatagride() {
    $("#role-datagride").datagrid({
        rownumbers:true,
        singleSelect:true,
        autoRowHeight:false,
        pagination:true,
        pageSize:3,
        method:'post',
        fitColumns:true,
        fit:true,
        pageList:[3,5,8,10],
        url:path+'/role/queryRole',
        columns:[[{title:"角色名字",field:"roleName"},
            {title:"角色代码",field:"roleCode"},
            {title:"是否可用",field:"valid",formatter:function (value, row, index) {
                if (value =='1'){
                    return "可用";
                }else {
                    return "不可用";
                }
            }},
            {title:"排序",field:"orders"},
            {title:"授权",field:"dbid",formatter:function (value, row, index) {
                return '<a href="javascript:void(0)" onclick="showGrantAuto2Role('+value+')">授权</a>'
            }}
        ]]
    });
}

function showGrantAuto2Role(value) {
    $("#roleId").val(value);
    $("#auth-treegrid").tree({
        url:path+'/auth/queryGrant?value='+value,
        method:'get',
        checkbox:true,
        cascadeCheck:false
    });

    $("#grant-window").window({
        modal: true,//灰色隔层
        closed: true,//是否可关闭
        width:300,
        height:340,
        iconCls: 'icon-edit',
        collapsible: false,//
        minimizable: false,
        maximizable: false,
        cloable:true
    }).window("open");
}
function saveForm(){
    //把checked的信息添加到准备好的数组里
    var nodes = $('#auth-treegrid').tree('getChecked');
    var authIds = [];
    for(var i = 0;i<nodes.length;i++){
        authIds.push(nodes[i].id);
    }
    var roleId = $("#roleId").val();


    var dataForm = {
        roleId:roleId,
        authIds:authIds
    };
    //用ajax发起请求接收求情参数
    $.ajax({
        url:path+'/role/grantAuths',
        method:'post',
        data:dataForm,
        traditional:true,//解决传递数组时ajax主动加[]的问题
        success:function (res) {
            if(res.msg){
                $('#grant-window').window('close');
            }
        }
    })
}