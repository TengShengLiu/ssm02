$(function () {
    $("select").combobox({
        panelHeight:50,
        editable:false
    });
    initUser();
});

//初始化加载数据
function initUser() {
    $("#user-datagride").datagrid({
        rownumbers:true,
        singleSelect:true,
        autoRowHeight:false,
        pagination:true,
        pageSize:5,
        method:'post',
        fitColumns:true,
        fit:true,
        pageList:[3,5,8,10],
        url:path+'/user/queryUser',
        onDblClickRow:addUser,
        columns:[[/*{title:'dbid',field:'dbid'},*/
            {title:'用户名',field:'userName'},
            {title:'密码',field:'password'},
            {title:'真实姓名',field:'realName'},
            {title:'是否可用',field:'valid',formatter:function (value, row, index) {
                if(value == '1'){
                    return "可用";
                }else{
                    return "不可用";
                }
            }},
            {title:'授予角色',width:100,field:'dbid',formatter:function (value, row, index) {
                return '<a href="javascript:void(0)" onclick="showGrantRole2User('+value+')">授予角色</a>'
            }}
        ]]
    })
}


function addUser(index,row) {
    console.log(index);
    console.log(row);
    if(index != undefined){
        title:'编辑用户',
        $("#addUserName").textbox("setValue",row.userName);
        $("#addPassword").textbox("setValue",row.password);
        $("#addRealName").textbox("setValue",row.realName);
        $("#addValid").combobox("setValue",row.valid);
    }else {
        title:'添加用户'
    }
    $("#user-window").window({

        modal: true,//灰色隔层
        closed: true,//是否可关闭
        width:300,
        height:340,
        iconCls: 'icon-edit',
        collapsible: false,//
        minimizable: false,
        maximizable: false,
        cloable:true,
        onBeforeClose:function () {
            $("#userForm").form("reset");
        }
    }).window("open");

}

function submit() {
    var userForm= $("#userForm").form().serialize();
    console.log(userForm);
    $.ajax({
        url:path+'/user/addUser',
        type:'post',
        data:userForm,
        success:function (res) {
            if(res.msg){
                //添加成功
                //成功修改
                $("#user-window").window("close");
                $("#user-datagride").datagrid("reload");
            }
        }
    })
}

function searchUser() {
    var data = {
        userName:$("#userName").textbox("getValue"),
        password:$("#password").textbox("getValue"),
        realName:$("#realName").textbox("getValue"),
        valid:$("#valid").combobox("getValue")

    };
    $("#user-datagride").datagrid('load',data);
}

function showGrantRole2User(value) {
    //准备数据,显示弹框里的所有角色
    $("#userId").val(value);
    $("#role-datagride").datagrid({
        url:path+'/role/getValidRole?userId='+value,
        method:'get',
        rownumber:true,
        columns:[[{field:'dbid',checkbox:true},
            {field:'roleName',title:'角色名称',width:100},
            {field:'roleCode',title:'角色代码',width:100}
        ]],
        onLoadSuccess:function (data) {
            var rows = data.rows;
            console.log(rows)//获取所有的rows
            for(var i = 0;i<rows.length;i++)
            if(rows[i].checked){
                $(this).datagrid("selectRow",i);//获取选中的row
            }
        }
    });
    $("#grant-window").window({
        modal: true,//灰色隔层
        closed: true,//是否可关闭
        width:250,
        resizable:true,
        iconCls: 'icon-edit',
        collapsible: false,//
        minimizable: false,
        maximizable: false,
        cloable:true
    }).window("open");

    // alert("haha")
}


function saveForm() {
    var nodes = $("#role-datagride").datagrid("getSelections");
    console.log(nodes);
    var roleIds = [];
    for(var i = 0;i<nodes.length;i++){
        roleIds.push(nodes[i].dbid);
        console.log(nodes[i].dbid);
    }
    var userId = $("#userId").val();
    console.log(userId);

    var dataForm = {
        userId:userId,
        roleIds:roleIds
    };
    //用ajax发起请求接收求情参数
    $.ajax({
        url:path+'/user/grantRoles',
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