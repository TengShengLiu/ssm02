<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2017/12/28
  Time: 9:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/common.jsp"%>
<html>
<head>
    <title>login page</title>
    <link rel="stylesheet" type="text/css" href="${path}/static/css/style.css">
    <script type="text/javascript">
            function login() {
                var dataForm={
                    userName:$("#userName").val(),
                    password:$("#password").val()
                };
                console.log(dataForm);
                $.ajax({
                    url:'${path}/user/login',
                    data:dataForm,
                    method:'post',
                    success:function (res) {
                        console.log(res);
                        if(res.msg){
                            location = path+'/hello';
                        }else{
                            $.messager.alert("错误提示","登陆失败","error")
                        }
                    }
                })
            }
    </script>
</head>
<body>
<div class="main-agileinfo">
    <h2>现在登录</h2>
    <form method="post">
        <input type="text" id="userName" name="userName" placeholder="请输入用户姓名" >
        <input type="password" id="password" name="password" class="password" placeholder="请输入密码">
        <ul>
            <li>
                <input type="checkbox" id="brand1" value="">
                <label for="brand1"><span></span>记得我</label>
            </li>
        </ul>
        <a href="#">忘记密码?
        </a><br>
        <div class="clear"></div>
        <input type="button" value="点击登录" onclick="login()">
    </form>
</div>
<div class="footer-w3l">
    <p class="agile"><a> &copy; 2017 xxxxxxxxxxxxx</a></p>
</div>
</body>
</html>
