package xyz.xlous.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import xyz.xlous.service.Impl.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/checkMobileServlet")
public class CheckMobileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mobile = request.getParameter("mobile");
        HashMap<String, Object> map = new HashMap<>();
        if (new UserServiceImpl().searchUserByMobile(mobile)) {
            // 手机号已存在，请去登录
            map.put("status",0);
            map.put("msg","手机号已注册，请前往登录！");
        } else {
            // 可以注册
            map.put("status",1);
            map.put("msg","请继续完成注册步骤！");
        }
        new ObjectMapper().writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
