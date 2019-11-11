<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>登录</title>
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
            <%--用户名--%>
            <%--<i class="layui-icon layui-icon-username admin-icon admin-icon-username"></i>--%>
            <div>
                <input type="text" name="mobile" id="mobile"
                       placeholder="请输入手机号" autocomplete="off"
                       class="layui-input admin-input admin-input-username">
            </div>
            <%--密码--%>
            <%--<i class="layui-icon layui-icon-password admin-icon"></i>--%>
            <div>
                <input type="password" name="password" id="pwd"
                       placeholder="请输入密码" autocomplete="off"
                       class="layui-input admin-input">
            </div>
            <%--验证码--%>
            <div>
                <input type="text" name="verify" id="checkCodeStr"
                       placeholder="请输入验证码" autocomplete="off"
                       class="layui-input admin-input admin-input-verify">
                <img class="admin-captcha" id="checkCode" src="createCheckCodeServlet" onclick="updateVerify()">
            </div>

            <div class="layui-form-item">
                <button type="button" class="layui-btn admin-button" lay-submit lay-filter="login">登录</button>
            </div>
            <div align="left" style="font-size: 15px; float: left">
                <a href="<%=request.getContextPath()%>/register.jsp">立即注册</a>
            </div>
            <div align="right" style="font-size: 15px;float: right">
                <a href="">忘记密码</a>
            </div>
        </form>

    </div>
</div>
<script type="text/javascript">
    layui.use(['form', 'layer', 'jquery'], function () {
        var form = layui.form;
        var $ = layui.jquery;

        //监听提交
        form.on('submit(login)', function (data) {
            $.ajax({
                type: 'POST',
                url: 'loginServlet',
                data: {
                    verify: $('#checkCodeStr').val(),
                    mobile: $('#mobile').val(),
                    password: $('#pwd').val(),
                },
                dataType: 'JSON',
                success: function (data) {
                    if (data.status == 2) {
                        layer.msg("验证码错误或不存在");
                        return;
                    } else if (data.status == 1) {
                        layer.msg("登录成功");
                        window.parent.location.href = './index.jsp';
                        return;
                    }
                    if (data.status == 0) {
                        layer.msg("手机号或密码输入错误");
                        return;
                    }
                }
            })
            // layer.msg(JSON.stringify(data.field));
            return false;
        });
    });

    // 点击更换验证码
    function updateVerify() {
        document.getElementById("checkCode").src = "createCheckCodeServlet?" + new Date().getTime();
    }

    $('#login').click(function () {
        if ($('#mobile').val().length == 0 || $('#pwd').val().length == 0 || $('#checkCodeStr').val().length == 0) {
            layer.msg("请输入手机号或密码！");
            return false;
        }
    });

    $('#mobile').blur(function () {
        var mobile = $('#mobile').val();

        if ($('#mobile').val().length == 0) {
            layer.msg('请输入手机号！');
            return false;
        } else if (!(/^1[3456789]\d{9}$/.test(mobile))) {
            $('#wr').removeAttr('hidden');
            $('#ri').attr('hidden', 'hidden');
            layer.msg("手机号码有误，请重新填写!");
            return false;
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
            return false;
        } else {
            $('#pri').removeAttr('hidden');
            $('#pwr').attr('hidden', 'hidden');
        }
    });
</script>
</body>
</html>
