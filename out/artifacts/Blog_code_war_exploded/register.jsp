<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>注册</title>
    <link rel="stylesheet" type="text/css" href="./layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="./res/css/main.css">
    <link rel="stylesheet" type="text/css" href="./res/css/admin.css">
    <script src="http://cdn.bootcss.com/jquery/1.12.3/jquery.min.js"></script>
    <script src="./layer/layer.js"></script>
    <script src="./layui/layui.js"></script>
</head>
<body>
<div id="container">
    <div></div>
    <div class="admin-login-background">
        <form class="layui-form" action="" onsubmit="return false">
            <%--<h1 align="center" style="font-size: 30px"> 注册页面 </h1>--%>
            <%--用户名--%>
            <!-- 对号 -->
            <i class="layui-icon admin-icon" style="color: green;font-weight: bolder;" id="ri" hidden></i>
            <!-- 错号 -->
            <i class="layui-icon admin-icon" style="color: red;font-weight: bolder;" id="wr" hidden>ဆ</i>
            <div>
                <input type="text" name="mobile" id="mobile"
                       placeholder="请输入手机号" autocomplete="off"
                       class="layui-input admin-input admin-input-username">

            </div>
            <%--用户名--%>
            <!-- 对号 -->
            <i class="layui-icon admin-icon" style="color: green;font-weight: bolder;" id="uri" hidden></i>
            <!-- 错号 -->
            <i class="layui-icon admin-icon" style="color: red;font-weight: bolder;" id="uwr" hidden>ဆ</i>
            <div>
                <input type="text" name="username" id="username"
                       placeholder="请输入用户名" autocomplete="off"
                       class="layui-input admin-input">

            </div>

            <%--密码--%>
            <!-- 对号 -->
            <i class="layui-icon admin-icon" style="color: green;font-weight: bolder;" id="pri" hidden></i>
            <!-- 错号 -->
            <i class="layui-icon admin-icon" style="color: red;font-weight: bolder;" id="pwr" hidden>ဆ</i>
            <div>
                <input type="password" name="password" id="pwd"
                       placeholder="请输入密码" autocomplete="off"
                       class="layui-input admin-input">

            </div>
            <%--确认密码--%>
            <!-- 对号 -->
            <i class="layui-icon admin-icon" style="color: green;font-weight: bolder;" id="rpri" hidden></i>
            <!-- 错号 -->
            <i class="layui-icon admin-icon" style="color: red;font-weight: bolder;" id="rpwr" hidden>ဆ</i>
            <div>
                <div style="float: left">
                    <input type="password" name="repassword" id="rpwd"
                           placeholder="请确认密码" autocomplete="off"
                           class="layui-input admin-input">
                </div>
            </div>

            <%--验证码--%>
            <div>
                <input type="text" name="verify" id="verify"
                       placeholder="请输入验证码" autocomplete="off"
                       class="layui-input admin-input admin-input-verify">
                <img class="admin-captcha" id="checkCode" src="createCheckCodeServlet" onclick="updateVerify()">
            </div>

            <div class="layui-form-item">
                <button type="button" class="layui-btn admin-button" lay-submit lay-filter="reg">注册</button>
            </div>
            <div align="left" style="font-size: 15px; float: left">
                <a href="<%=request.getContextPath()%>/login.jsp">已有账号？点击登录</a>
            </div>
            <div align="right" style="font-size: 15px;float: right">
                <a href="">忘记密码</a>
            </div>
        </form>

    </div>
</div>
<script type="text/javascript">

    var mobile = false;

    layui.use(['form', 'layer', 'jquery'], function () {
        var form = layui.form;
        var $ = layui.jquery;

        //监听提交
        form.on('submit(reg)', function (data) {
            $.ajax({
                type: 'POST',
                url: 'registerServlet',
                data: {
                    verify:$('#verify').val(),
                    mobile: $('#mobile').val(),
                    username:$('#username').val(),
                    password:$('#pwd').val()
                },
                dataType: 'JSON',
                success: function (data) {
                    if (data.status == 1) {
                        $('#ri').removeAttr('hidden');
                        $('#wr').attr('hidden', 'hidden');
                        window.parent.location.href = './login.jsp';
                        return ;
                    } else if (data.status == 0) {
                        $('#wr').removeAttr('hidden');
                        $('#ri').attr('hidden', 'hidden');
                        layer.msg(data.msg);
                        window.parent.location.href="./register.jsp";
                        setTimeout("location.href = '" + url + "'",5000)
                        return ;
                    }
                }
            })
            return false;
        });
    });

    // 点击更换验证码
    function updateVerify() {
        document.getElementById("checkCode").src = "createCheckCodeServlet?" + new Date().getTime();
    }

    $('#mobile').blur(function () {

        if ($('#mobile').val().length == 0) {
            layer.msg('请输入手机号！');
        } else if (!(/^1[3456789]\d{9}$/.test($('#mobile').val()))) {
            $('#wr').removeAttr('hidden');
            $('#ri').attr('hidden', 'hidden');
            layer.msg("手机号码有误，请重新填写!");
        } else {
            $.ajax({
                type: 'POST',
                url: 'checkMobileServlet',
                data: {
                    mobile: $('#mobile').val(),
                },
                dataType: 'JSON',
                success: function (data) {
                    if (data.status == 1) {
                        $('#ri').removeAttr('hidden');
                        $('#wr').attr('hidden', 'hidden');
                        mobile = true;
                    } else if (data.status == 0) {
                        $('#wr').removeAttr('hidden');
                        $('#ri').attr('hidden', 'hidden');
                        layer.msg(data.msg);
                    }
                }
            })
        }
    });

    $('#username').blur(function () {
        if ($('#username').val().length == 0) {
            layer.msg('请输入用户名！');
        } else {
            $.ajax({
                type: 'POST',
                url: 'checkUnameServlet',
                data: {
                    username: $('#username').val(),
                },
                dataType: 'JSON',
                success: function (data) {
                    if (data.status == 1) {
                        $('#uri').removeAttr('hidden');
                        $('#uwr').attr('hidden', 'hidden');
                        username = true;
                    } else if (data.status == 0) {
                        $('#uwr').removeAttr('hidden');
                        $('#uri').attr('hidden', 'hidden');
                        layer.msg(data.msg);
                    }
                }
            })
        }
    });



    // 为密码添加正则验证
    $('#pwd').blur(function () {
        var reg = /^[\w]{6,12}$/;
        if (!($('#pwd').val().match(reg))) {
            //layer.msg('请输入合法密码');
            $('#pwr').removeAttr('hidden');
            $('#pri').attr('hidden', 'hidden');
            layer.msg('请输入合法密码');
        } else {
            $('#pri').removeAttr('hidden');
            $('#pwr').attr('hidden', 'hidden');
        }
    });
    //验证两次密码是否一致
    $('#rpwd').blur(function () {
        if ($('#pwd').val().length == 0) {
            $('#rpwr').removeAttr('hidden');
            $('#rpri').attr('hidden', 'hidden');
            layer.msg('请先输入密码!');
        } else if ($('#pwd').val() != $('#rpwd').val()) {
            $('#rpwr').removeAttr('hidden');
            $('#rpri').attr('hidden', 'hidden');
            layer.msg('两次输入密码不一致!');
            return false;
        } else {
            $('#rpri').removeAttr('hidden');
            $('#rpwr').attr('hidden', 'hidden');
            password = true;
        }
    });
    $('#verify').blur(function () {
        if ($('#verify').val().length != 4) {
            layer.msg('验证码位数有误！');
        }
    });

</script>

</body>
</html>