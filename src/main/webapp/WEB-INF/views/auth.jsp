<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 817/12/8
  Time: 9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common.jsp"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>权限</title>
    <script type="text/javascript" src="${path}/static/js/auth.js"></script>
</head>
<body>
<%--以哪个字段显示为tree--%>
<table id="auth-treegrid">

</table>
<%--右键点击弹出的弹框,默认隐藏--%>
<div id="mm" class="easyui-menu" style="width:18px;">
    <div onclick="AddOrEditAuthForm('添加')" data-options="iconCls:'icon-add'">增加子节点</div>
    <div onclick="AddOrEditAuthForm('编辑')" data-options="iconCls:'icon-edit'">编辑</div>
    <div onclick="refreshAuth()" data-options="iconCls:'icon-reload'">刷新</div>
</div>
<div id="auth-window" title="编辑" data-options="iconCls:'icon-edit'">
    <div class="easyui-panel" title="" style="width:100%;max-width:400px;padding:30px 60px;">
        <form id="authForm" method="post">
            <input type="hidden" id="dbid" name="dbid"/>
            <input type="hidden" id="parentId" name="parentId"/>
            <div style="margin-bottom:8px">
                <input class="easyui-textbox" style="width:100%" id="parentName" name="parentName"  data-options="label:'上级节点：',readonly:true">
            </div>
            <div style="margin-bottom:8px">
                <input class="easyui-textbox" style="width:100%" id="layer" name="layer"  data-options="label:'当前层级:',readonly:true">
            </div>
            <div style="margin-bottom:8px">
                <input class="easyui-textbox" style="width:100%" id="authName" name="authName"  data-options="label:'权限名称:'">
            </div>
            <div style="margin-bottom:8px">
                <input class="easyui-textbox" style="width:100%" id="authCode" name="authCode"  data-options="label:'权限编码:'">
            </div>
            <div style="margin-bottom:8px">
                <input class="easyui-textbox" style="width:100%" id="authURL" name="authURL"  data-options="label:'URL:',multiline:true">
            </div>
            <div style="margin-bottom:8px">
                <input class="easyui-textbox" style="width:100%" id="order" name="orders"  data-options="label:'排序:',multiline:true">
            </div>

            <div style="margin-bottom:20px" >
                <select class="easyui-combobox" id="valid" name="valid" style="width:100%" label="是否可用:" <%--data-options="panelHeight=50,editable=false"--%>>
                    <option value="1">可用</option><option value="0">不可用</option>
                </select>
            </div>

            <div style="margin-bottom:8px">
                <select class="easyui-combobox" id="type" name="type" style="width:100%" label="类型:"  <%--data-options="panelHeight=50,editable=false"--%>>
                    <option value="1">模块</option>
                    <option value="2">资源</option>
                </select>
            </div>
        </form>
    </div>
        <div style="text-align:center;padding:5px 0">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">提交</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">清除</a>
        </div>
</div>
</body>
</html>
