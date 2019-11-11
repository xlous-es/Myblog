package xyz.xlous.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import xyz.xlous.pojo.User;
import xyz.xlous.service.Impl.UserServiceImpl;
import xyz.xlous.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 验证码校验
        String verifycode = request.getParameter("verify");
        HttpSession session = request.getSession();
        String verify = (String) session.getAttribute("verify");
        session.removeAttribute("verify");

        HashMap<String, Object> map = new HashMap<>();
        if (verify == null || verify.length() == 0 || !verify.equalsIgnoreCase(verifycode)){
            // 验证码不正确
            map.put("msg","验证码错误或不存在！");
            map.put("status",2);
            new ObjectMapper().writeValue(response.getWriter(),map);
            request.getRequestDispatcher("/register.jsp").forward(request,response);
            return;
        }
        User user = new User();
        user.setUserTel(request.getParameter("mobile"));
        user.setPassword(request.getParameter("password"));
        user.setUserName(request.getParameter("username"));
        UserService service = new UserServiceImpl();
        boolean bool = service.addUser(user);
        if (bool){
            map.put("msg","注册成功！");
            // 登录成功
            map.put("status",1);
            new ObjectMapper().writeValue(response.getWriter(),map);
        }else{
            map.put("msg","注册失败，请重试！");
            // 登录失败
            map.put("status",0);
            new ObjectMapper().writeValue(response.getWriter(),map);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
