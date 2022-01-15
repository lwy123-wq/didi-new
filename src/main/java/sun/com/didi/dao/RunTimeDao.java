package sun.com.didi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import sun.com.didi.entity.Login;
import sun.com.didi.entity.Recruit;

import java.sql.ResultSet;
import java.sql.SQLException;
@Repository
public class RunTimeDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Recruit runtime(String company){
        String sql="select UTCTime from Recruit where Rec_company=?";
        Recruit recruit=new Recruit();
        jdbcTemplate.query(sql, new Object[]{company}, new RowCallbackHandler() {

            @Override
            public void processRow(ResultSet rs) throws SQLException {
                recruit.setUTCTime(rs.getString("UTCTime"));
            }
        });
        return recruit;
    }

    public int update(String time, String company){
        String sql="UPDATE Recruit SET UTCTime=? where Rec_company=?";
        return jdbcTemplate.update(sql,time,company);

    }

    public int insertime(Recruit UTC){
        String sql="INSERT INTO Recruit (UTCTime)VALUES(?)";
        return jdbcTemplate.update(sql, UTC.getUTCTime());
    }

}
