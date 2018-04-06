<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2017/12/22
  Time: 9:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <%@include file="common.jsp" %>
    <c:set value="${pageContext.request.contextPath}" var="path"/>
    <script type="text/javascript" src="${path}/static/js/user.js"></script>
    <title>User page</title>
</head>
<body class="easyui-layout">
<%--获取未拥有的资源--%>
<c:forEach items="${sessionScope.resources}" var="resource">
    <c:set var="resource" value="${resource}"/>
    <p>${resource}</p>
</c:forEach>
<script type="text/javascript">
    console.log(${resource});
</script>


<div data-options="region:'north',border:false" style="height:40px;padding:7px">&nbsp;&nbsp;&nbsp;
    <input class="easyui-textbox" id="userName" style="width:17%" data-options="prompt:'输入用户姓名'">&nbsp;&nbsp;&nbsp;
    <input class="easyui-textbox" id="password" style="width:17%" data-options="prompt:'输入密码'">&nbsp;&nbsp;&nbsp;
    <input class="easyui-textbox" id="realName" style="width:17%" data-options="prompt:'输入真实姓名'">&nbsp;&nbsp;&nbsp;
    <select class="easyui-combobox" id="valid" style="width:17%" label="是否有效" labelPosition="left" data-options="editable:false,panelHeight:50">&nbsp;&nbsp;&nbsp;
        <option value="1">可用</option>
        <option value="0">不可用</option>
    </select>&nbsp;&nbsp;&nbsp;
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'" <%--<c:if test="${resource==14}">onclick="searchUser()"</c:if>--%>  onclick="searchUser()" style="width:80px">搜索</a>&nbsp;&nbsp;&nbsp;
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'" <%--<c:if test="${resource==11}">onclick="addUser()"</c:if>--%>  onclick="addUser()" style="width:80px">添加</a>
</div>
<div data-options="region:'center'">
    <table id="user-datagride" >

    </table>

    <%--添加角色--%>
    <div id="user-window" data-options="iconCls:'icon-edit'">
        <div class="easyui-panel" title="" style="width:100%;max-width:400px;padding:30px 60px;">
            <form id="userForm" method="post">
                <input type="hidden" id="dbid" name="dbid"/>
                <div style="margin-bottom:8px">
                    <input class="easyui-textbox" style="width:100%" id="addUserName" name="userName"  data-options="label:'用户名：',multiline:true">
                </div>
                <div style="margin-bottom:8px">
                    <input class="easyui-textbox" style="width:100%" id="addPassword" name="password"  data-options="label:'密码:',multiline:true">
                </div>
                <div style="margin-bottom:8px">
                    <input class="easyui-textbox" style="width:100%" id="addRealName" name="realName"  data-options="label:'真实姓名:',multiline:true">
                </div>
                <div style="margin-bottom:20px" >
                    <select class="easyui-combobox" id="addValid" name="valid" style="width:100%" label="是否可用:" <%--data-options="panelHeight=50,editable=false"--%>>
                        <option value="1">可用</option><option value="0">不可用</option>
                    </select>
                </div>
            </form>
        </div>
        <div style="text-align:center;padding:5px 0">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submit()" style="width:80px">提交</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">清除</a>
        </div>
    </div>


    <%--授予角色--%>
    <div id="grant-window" title="授予角色窗口" data-options="iconCls:'icon-edit'">
        <input type="hidden" name="userId" id="userId">
        <table id="role-datagride">
        </table>
        <div style="text-align:center;padding:5px 0">
            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="saveForm()" style="width:80px">保存</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="clearForm()" style="width:80px">清除</a>
        </div>
    </div>

</div>
</body>
</html>
