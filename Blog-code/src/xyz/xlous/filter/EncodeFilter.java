package xyz.xlous.filter;

import xyz.xlous.pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class EncodeFilter implements Filter {

    private static final String[] No_Filter = {"/login.jsp","/register.jsp","/checkMobileServlet","/checkUnameServlet",
            "/createCheckCodeServlet","/LoginServlet","/registerServlet"};

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        String path = request.getRequestURI();
        String mobile = (String)session.getAttribute("mobile");
        /*if (path.indexOf("/login.jsp") > -1 || path.indexOf("/register.jsp") > -1){
            chain.doFilter(request,response);
            return ;
        }*/
        for (int i = 0; i < No_Filter.length; i++) {
            if (path.indexOf(No_Filter[i])>-1){
                chain.doFilter(request,response);
                return ;
            }
        }
        if (mobile == null || "".equals(mobile)){
            response.sendRedirect("./login.jsp");
        }else {
            chain.doFilter(request,response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
