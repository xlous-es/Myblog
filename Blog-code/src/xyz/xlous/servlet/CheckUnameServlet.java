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

@WebServlet("/checkUnameServlet")
public class CheckUnameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        HashMap<String, Object> map = new HashMap<>();
        if (new UserServiceImpl().findUserByUsername(username)) {
            map.put("status",0);
            map.put("msg","用户名已存在，请更换！");
        } else {
            map.put("status",1);
            map.put("msg","请继续完成注册步骤！");
        }
        new ObjectMapper().writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
