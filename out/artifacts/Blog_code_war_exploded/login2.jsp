<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理-登陆</title>
    <link rel="stylesheet" href="res/layui/css/layui.css">
    <link rel="stylesheet" href="res/css/admin.css">
    <script src="http://cdn.bootcss.com/jquery/1.12.3/jquery.min.js"></script>
</head>
<body>
<div id="container">
    <div></div>
    <div class="admin-login-background">
        <form class="layui-form" action="">
            <div>
                <i class="layui-icon layui-icon-username admin-icon admin-icon-username"></i>
                <input type="text" name="username" placeholder="请输入用户名"
                       autocomplete="off"
                       class="layui-input admin-input admin-input-username">
            </div>
            <div>
                <i class="layui-icon layui-icon-password admin-icon admin-icon-password"></i>
                <input type="password" name="password"
                       placeholder="请输入密码"
                       autocomplete="off"
                       class="layui-input admin-input">
            </div>
            <div>
                <input type="text" name="verify"
                       placeholder="请输入验证码"
                       autocomplete="off"
                       class="layui-input admin-input admin-input-verify">
                <img class="admin-captcha" src=""
                     onclick="updateVerify()">
            </div>
            <button class="layui-btn admin-button" lay-submit lay-filter="formDemo">登陆</button>


        </form>
    </div>
</div>
<div>hello</div>
<script>

    function updateVerify() {

    }


</script>
</body>
</html>