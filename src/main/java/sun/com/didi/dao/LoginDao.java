package sun.com.didi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import sun.com.didi.entity.Login;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
* 操作数据库
* */
@Repository
public class LoginDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Login findByName(String name) {

        final Login user = new Login();
        String sql = "SELECT * FROM Login WHERE name=?";
        jdbcTemplate.query(sql, new Object[]{name}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setName(resultSet.getString(2));
            }
        });
        return user;
    }
    public int insertUser(Login register){

        String sql="INSERT INTO Login (name,password,email)VALUES(?,?,?)";
        return jdbcTemplate.update(sql, register.getName(), register.getPasswd(), register.getEmail());
    }

    public Login findByNameAndPassword(String username, String password) {
        final Login user = new Login();
        String sql = "SELECT * FROM Login WHERE name=? AND password=?";
        jdbcTemplate.query(sql, new Object[]{username, password}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setName(resultSet.getString(2));
                user.setPasswd(resultSet.getString(5));
            }
        });
        return user;
    }
    //找回密码
    public Login findPassword(String username,String email){
        final  Login user=new Login();
        String sql="SELECT * FROM Login WHERE name=? AND email=?";
        jdbcTemplate.query(sql, new Object[]{username, email}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                user.setPasswd(rs.getString(5));
            }
        });
        return user;
    }
}
