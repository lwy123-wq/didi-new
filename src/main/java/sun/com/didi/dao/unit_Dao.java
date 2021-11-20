package sun.com.didi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import sun.com.didi.entity.Unit;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class unit_Dao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public int unit_insert(Unit unit_user){

        String sql="INSERT INTO unit_user (name,password,email)VALUES(?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, unit_user.getRec_category(), unit_user.getRec_company(),
                unit_user.getRec_Duration(),unit_user.getRec_logo(),unit_user.getRec_salary(),unit_user.getRec_logo());
    }

    public Unit findByUnit(String name,String logo){
      final Unit unit=new Unit();
     String sql = "SELECT * FROM Login WHERE Rec_company=? AND Rec_logo";
        jdbcTemplate.query(sql, new Object[]{name,logo}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                unit.setRec_company(rs.getString(2));
                unit.setRec_logo(rs.getString(4));
            }
        });
        return unit;
    }

}
