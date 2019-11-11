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
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
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
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        User user = new User();
        user.setUserTel(request.getParameter("mobile"));
        user.setPassword(request.getParameter("password"));
        UserService service = new UserServiceImpl();
        User loginUser = service.login(user);
        if (loginUser != null){
//            request.getSession().setAttribute("user",loginUser);
            request.getSession().setAttribute("mobile",loginUser.getUserTel());
            map.put("msg","登录成功！");
            // 登录成功
            map.put("status",1);
            new ObjectMapper().writeValue(response.getWriter(),map);
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }else{
            map.put("msg","手机号或密码错误！");
            // 登录失败
            map.put("status",0);
            new ObjectMapper().writeValue(response.getWriter(),map);
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
