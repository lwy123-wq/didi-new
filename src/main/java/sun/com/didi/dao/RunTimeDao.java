package sun.com.didi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import sun.com.didi.entity.Login;
import sun.com.didi.entity.Recruit;
import sun.com.didi.entity.Report;

import java.sql.ResultSet;
import java.sql.SQLException;
@Repository
public class RunTimeDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Report runtime(String user){
        String sql="select Time from Report where user=?";
        Report report=new Report();
        jdbcTemplate.query(sql, new Object[]{user}, new RowCallbackHandler() {

            @Override
            public void processRow(ResultSet rs) throws SQLException {
                report.setTime(rs.getString("Time"));
            }
        });
        return report;
    }

    public int update(String time, String user){
        String sql="UPDATE Report SET Time=? where user=?";
        return jdbcTemplate.update(sql,time,user);

    }
/*续约*/
    public int insertime(Report UTC){
        String sql="INSERT INTO Report (UTCTime)VALUES(?)";
        return jdbcTemplate.update(sql, UTC.getTime());
    }
    public Report selectCompany(String name){
        String sql="select company from Report where user=?";
        Report report=new Report();
        jdbcTemplate.query(sql, new Object[]{name}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                report.setCompany(rs.getString(1));
            }
        });
        return report;
    }
    public Report selectUser(String name){
        String sql="select user from Report where company=?";
        Report report=new Report();
        jdbcTemplate.query(sql, new Object[]{name}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                report.setUser(rs.getString(1));
            }
        });
        return report;
    }

}
