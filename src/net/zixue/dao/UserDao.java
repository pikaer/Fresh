package net.zixue.dao;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import net.zixue.bean.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import java.sql.SQLException;

public class UserDao {

    public boolean checkUser(String name){

        try {
            ComboPooledDataSource dataSource=new ComboPooledDataSource();
            QueryRunner queryRunner=new QueryRunner(dataSource);
            String sql="select name from user where name=?";
            User user = queryRunner.query(sql, new BeanHandler<User>(User.class),name);
            //如果没有查询到数据 说明这个用户名没有注册过
            return user == null;
        } catch (SQLException e) {
            e.printStackTrace();
            return  false;
        }
    }

    public boolean register(User user) {
        try {

            ComboPooledDataSource dataSource=new ComboPooledDataSource();
            QueryRunner queryRunner=new QueryRunner(dataSource);
            String sql="insert into user values(null,?,?,?)";
            int row = queryRunner.update(sql, user.getName(), user.getPassword(), user.getEmail());

            //行数大于零说明注册成功
            return row > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return  false;
        }

    }
}
