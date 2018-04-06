<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2017/12/22
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common.jsp" %>
<html>
<head>
    <c:set value="${pageContext.request.contextPath}" var="path"/>
    <script type="text/javascript" src="${path}/static/js/role.js"></script>
    <title>Role Page</title>
</head>
<body class="easyui-layout">

    <div data-options="region:'north',border:false" style="height:40px;padding:7px">&nbsp;&nbsp;&nbsp;
        <input class="easyui-textbox" id="roleName" name="roleName" style="width:25%" data-options="prompt:'输入角色名称'">&nbsp;&nbsp;&nbsp;
        <input class="easyui-textbox" id="roleCode" name="roleCode" style="width:25%" data-options="prompt:'输入角色代码'">&nbsp;&nbsp;&nbsp;
        <select class="easyui-combobox" id="valid" name="valid" style="width:20%" label="是否有效" labelPosition="left" data-options="editable:false,panelHeight:50">&nbsp;&nbsp;&nbsp;
            <option value="1">可用</option>
            <option value="0">不可用</option>
        </select>&nbsp;&nbsp;&nbsp;
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchRole()" style="width:80px">搜索</a>&nbsp;&nbsp;&nbsp;
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addRole()" style="width:80px">添加</a>
    </div>

    <div data-options="region:'center'">
        <table id="role-datagride" >
        </table>

        <%--添加角色--%>
        <div id="role-window" title="添加角色" data-options="iconCls:'icon-edit'">
            <div class="easyui-panel" title="" style="width:100%;max-width:400px;padding:30px 60px;">
                <form id="roleForm" method="post">
                    <input type="hidden" id="dbid" name="dbid"/>
                    <div style="margin-bottom:8px">
                        <input class="easyui-textbox" style="width:100%" id="addRoleName" name="roleName"  data-options="label:'角色名：',multiline:true">
                    </div>
                    <div style="margin-bottom:8px">
                        <input class="easyui-textbox" style="width:100%" id="addRoleCode" name="roleCode"  data-options="label:'角色代码:',multiline:true">
                    </div>
                    <div style="margin-bottom:8px">
                        <input class="easyui-textbox" style="width:100%" id="addOrder" name="orders"  data-options="label:'排序:',multiline:true">
                    </div>
                    <div style="margin-bottom:20px" >
                        <select class="easyui-combobox" id="addValid" name="valid" style="width:100%" label="是否可用:" <%--data-options="panelHeight=50,editable=false"--%>>
                            <option value="1">可用</option><option value="0">不可用</option>
                        </select>
                    </div>
                </form>
            </div>
            <div style="text-align:center;padding:5px 0">
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">提交</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">清除</a>
            </div>
        </div>

        <%--授权--%>
        <div id="grant-window" title="授权" data-options="iconCls:'icon-edit'">
            <div class="easyui-panel" title="" style="width:100%;max-width:400px;padding:30px 60px;">
                <input type="hidden" name="roleId" id="roleId">
                <table id="auth-treegrid"></table>
                <ul id="tt" class="easyui-tree" data-options="animate:true,checkbox:true">
                <%--<ul id="tt" class="easyui-tree" data-options="url:'${path}/static/temp/tree_data1.json',method:'get',animate:true,checkbox:true">--%>
                </ul>
            </div>
            <div style="text-align:center;padding:5px 0">
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="saveForm()" style="width:80px">保存</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="clearForm()" style="width:80px">清除</a>
            </div>
        </div>

    </div>
</body>
</html>
