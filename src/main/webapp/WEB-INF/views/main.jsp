<%@ page import="com.hwua.ssm.po.Auth" %>
<%@ page import="java.util.List" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2017/12/19
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set value="${pageContext.request.contextPath}" var="path"/>
<%@include file="common.jsp"%>
<html>
<head>
    <meta charset="UTF-8">
    <%--<script type="text/javascript" src="${path}/static/js/main.js"></script>--%>
</head>
<body class="easyui-layout">
<div data-options="region:'west',split:true,title:'功能模块'" style="width:150px;padding:0;">
    <div class="easyui-accordion" data-options="fit:true">
        <c:forEach items="${sessionScope.auths}" var="auth">
            <div title="${auth.authName}">
                <c:set var="auth" value="${auth}"/>
                <ul id="tree-${auth.dbid}"></ul>
                <%--<c:forEach  items="${sessionScope.authTrees}" var="authTree">
                    <p>${authTree}</p>
                    <p>hehe</p>
                    <c:if test="${auth.dbid == authTree.parentId}">
                        <p>haha</p>
                        <div>${authTree.authName}</div>
                    </c:if>
                </c:forEach>--%>
                <%--<p>${auth.children}</p>--%>
                <script type="text/javascript">
                    <%
                        Auth auth = (Auth) pageContext.getAttribute("auth");
                        List<Auth> children = auth.getChildren();
                        String json = JSON.toJSONString(children);
                        pageContext.setAttribute("json",json);
                    %>

                    var treeData = '${json}';//注意单引号和双引号的冲突，Unexpected identifier
                    treeData = JSON.parse(treeData);
                    $("#tree-${auth.dbid}").tree({
                        data:treeData,
                        onClick:function (node) {
                            if (node.length != 0){
                                //打开页面,如果有此标签
                                if($("#main-tab").tabs("exists",node.text)){
//                            已有，打开已有的
                                    $("#main-tab").tabs("select",node.text)
                                }else {
                                    $("#main-tab").tabs("add",{
                                        title:node.text,
                                        content:"<iframe width='100%' height='100%' frameborder='0'"+"src="+path+node.authURL+"/>",
                                        closable:true
                                    });
                                }
                            }
                        }
                    });
                </script>

            </div>
        </c:forEach>
    </div>
</div>

<div data-options="region:'north',collapsible:false<%--,tools:'#tool'--%>" style="width:150px;padding:0;">
    <div style="float: left;font-size: 15px;color: #222222;padding-top: 5px">
        <strong>人力资源管理系统</strong>
    </div>
    <div style="float: right;font-size: 15px;color: #222222;padding-top: 5px" >
        <strong>
            欢迎&nbsp;&nbsp;
            <a href="javascript:void(0)" style="margin-right:10px;color: #222222" onclick="changPwd()">${sessionScope.user.userName}</a>
            <a href="user/exit" style="margin-right:10px">注销</a>
        </strong>
    </div>
</div>

<div data-options="region:'center',title:'业务面板'">
    <div id="main-tab" class="easyui-tabs" data-options="fit:true"></div>
</div>
</body>
</html>