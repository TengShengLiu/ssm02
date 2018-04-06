/*    function append() {
           var row =$("#auth-treegrid").treegrid("getSelected");//获取treegride选中的行
           console.log(row)
           var parent = $("#auth-treegrid").treegrid("getParent",row.dbid);
           $("#parentName").textbox("setValue",parent.authName);
           $("#layer").textbox("setValue",row.layer);
           $("#auth-window").window({
               modal: true,//灰色隔层
               closed: true,//是否可关闭
               width:300,
               height:400,
               iconCls: 'icon-edit',
               collapsible: false,//
               minimizable: false,
               maximizable: false,
               cloable:true,
               title:'编辑'
           }).window("open");
       }*/

function submitForm() {
    //ajax提交表单
    //1.准备请求参数
    var formData = $("#authForm").form().serialize();
    console.log(formData)
    $.ajax({
        url:path+'/auth/addOrUpdate',
        type:'post',
        data:formData,
        success:function (res) {
            if(res.msg){
                //成功修改
                $("#auth-window").window("close");
                $("#auth-treegrid").treegrid("reload");
            }
        }
    })
}

function refreshAuth() {
    $("#auth-treegrid").treegrid("reload");
}

function AddOrEditAuthForm(action) {
    if(action=="添加"){
        console.log('add');
        var row =$("#auth-treegrid").treegrid("getSelected");//获取treegride选中的行
        var parent = $("#auth-treegrid").treegrid("getParent",row.dbid);
        $("#parentName").textbox("setValue",row.authName);
        $("#layer").textbox("setValue",row.layer+1);
        $("#parentId").val(row.dbid);
    }else{
        console('edit');
        var row =$("#auth-treegrid").treegrid("getSelected");//获取treegride选中的行
        var parent = $("#auth-treegrid").treegrid("getParent",row.dbid);
        $("#parentName").textbox("setValue",parent.authName);
        $("#layer").textbox("setValue",row.layer);
        $("#authName").textbox("setValue",row.authName);
        $("#authCode").textbox("setValue",row.authCode);
        $("#authURL").textbox("setValue",row.authURL);
        $("#order").textbox("setValue",row.orders);

        $("#type").combobox("setValue",row.type);
        $("#valid").combobox("setValue",row.valid);

        $("#dbid").val(row.dbid);
        $("#parentId").val(row.parentId);
    }
    $("#auth-window").window({
        modal: true,//灰色隔层
        closed: true,//是否可关闭
        width:300,
        height:400,
        iconCls: 'icon-edit',
        collapsible: false,//
        minimizable: false,
        maximizable: false,
        cloable:true,
        title:action,
        onBeforeClose:function () {
            $("#authForm").form("reset");
        }
    }).window("open");
}

function onContextMenu(e,row) {
    if(row){
        e.preventDefault();
        /*选中指定单元行*/
        $(this).treegrid("select",row.dbid);
        /*弹出弹框*/
        $("#mm").menu("show",{
            left:e.pageX,
            top:e.pageY
        })
    }
}
$(function () {
    $("select").combobox({
        panelHeight:50,
        editable:false
    })
    $("#auth-treegrid").treegrid({
        url:path+"/auth/getAllAuth",
        idField:"dbid",
        method:"get",
        fit:true,
        fitColumns:true,
        rownumbers:true,
        treeField:"authName",
        onContextMenu: onContextMenu,
        columns:[[
            {field:"authName",title:"权限名称",width:220},
            {field:"authCode",title:"权限代码",width:180},
            {field:"authURL",title:"URL",width:180},
            {field:"type",title:"类型",width:180,
                formatter:function (value,row,index) {
                    if(value=="1"){
                        return '模块';
                    }else {
                        return '资源';
                    }
                }},
            {field:"parentId",title:"parentId",width:180},
            {field:"orders",title:"orders",width:180},
            {field:"valid",title:"有效性",width:180,
                formatter:function (value,row,index) {
                    if(value=="1"){
                        return '显示';
                    }else {
                        return '<span style="color: red">不显示</span>';
                    }
                }},
            {field:"layer",title:"层级",width:180}
        ]]
    })
})