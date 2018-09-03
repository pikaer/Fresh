package net.zixue.web;
import net.zixue.bean.User;
import net.zixue.service.UserService;
import java.io.IOException;

@javax.servlet.annotation.WebServlet(name = "RegisterServlet",urlPatterns="/register")
public class RegisterServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String name = request.getParameter("name");
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        User user=new User();
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);

        UserService userService=new UserService();
        boolean register = userService.register(user);
        if (register) {
            response.sendRedirect(request.getContextPath()+"login.jsp");
        }else {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("注册失败");
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
