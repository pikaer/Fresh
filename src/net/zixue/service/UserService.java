package net.zixue.service;
import net.zixue.bean.User;
import net.zixue.dao.UserDao;
import java.sql.SQLException;

public class UserService {
    //1. 判断注册用户是否存在
    public boolean register(User user) {
        boolean register=false;
        UserDao userDao = new UserDao();
        boolean checkUser = userDao.checkUser(user.getName());
        //2. 如果不存在就将用户信息添加到数据库
        if (checkUser) {
            register = userDao.register(user);
        }
        return register;
    }

    public User login(String name, String password) throws SQLException {
        UserDao userDao=new UserDao();
        User user = userDao.login(name, password);
        return  user;
    }
}
