package net.zixue.web;


import net.zixue.bean.User;
import net.zixue.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        UserService userService=new UserService();
        User user=null;
        try {
            //调用service中登录方法
            user = userService.login(name, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user!=null){
            //登录成功后我们再获取是否保存密码的信息，如果失败了保存密码就没有意义了
            String remember = request.getParameter("remember");
            if (remember.equals("yes")){
                // 将用户名和密码加入到cookie中
                Cookie nameCookie = new Cookie("name", name);
                Cookie passwordCookie = new Cookie("password", password);
                //设置cookie的有效期 防止销毁
                nameCookie.setMaxAge(60*10);
                passwordCookie.setMaxAge(60*10);
                //将cookie发送给客户端保存
                response.addCookie(nameCookie);
                response.addCookie(passwordCookie);

            }
            /* 登录成功跳转生鲜种类列表界面 */
            response.sendRedirect(request.getContextPath()+"/category-list.jsp");
        }else {
            //登录失败提示
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("用户登录失败");
        }
    }
}
