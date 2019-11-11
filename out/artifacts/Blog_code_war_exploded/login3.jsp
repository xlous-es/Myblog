<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="./res/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="./res/css/main.css">
    <link rel="stylesheet" type="text/css" href="./res/css/admin.css">
    <script src="http://cdn.bootcss.com/jquery/1.12.3/jquery.min.js"></script>
    <script src="layer/layer.js"></script>
    <script src="res/layui/layui.js"></script>
    <style>
        /*.admin-login-background {
            background-color: red;
            width: 300px;
            height: 300px;
            position: absolute;
            left: 50%;
            top: 50%;
            margin-left: -150px;
            margin-top: -100px;
        }*/
    </style>
</head>
<body>
<div id="container">
    <div></div>
    <div class="admin-login-background">
        <form class="layui-form" action="" onsubmit="return false">
            <%--用户名--%>
            <i class="layui-icon layui-icon-username admin-icon admin-icon-username"></i>
            <div>
                <input type="text" name="mobile" id="mobile"
                       placeholder="请输入手机号" autocomplete="off"
                       class="layui-input admin-input admin-input-username">
            </div>
        </form>

    </div>
</div>
</body>
</html>
