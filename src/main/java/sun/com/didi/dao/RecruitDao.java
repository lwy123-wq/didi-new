package sun.com.didi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sun.com.didi.entity.Requirement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RecruitDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public Requirement SelectRecruit(int id){
        final Requirement re=new Requirement();
        String sql= "SELECT * FROM   recruitmentrequirements WHERE id=? ";
        jdbcTemplate.query(sql, new Object[]{id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                re.setOccupationalCategory(rs.getString(3));
                re.setRemainingPositions(rs.getString(4));
                re.setSalaryRequirements(rs.getString(5));
                re.setCompany(rs.getString(2));
                re.setNumber(rs.getString(6));
            }
        });
        return re;
    }

    public List<Requirement> Select(){
        String sql= "SELECT * FROM recruitmentrequirements";
        List<Requirement> list = jdbcTemplate.query(sql, new RowMapper<Requirement>() {
            @Override
            public Requirement mapRow(ResultSet rs, int rowNum) throws SQLException {
                Requirement re = new Requirement();
                re.setCompany(rs.getString("Company"));
                re.setNumber(rs.getString("Number"));
                re.setRemainingPositions(rs.getString("RemainingPositions"));
                re.setOccupationalCategory(rs.getString("OccupationalCategory"));
                re.setSalaryRequirements(rs.getString("SalaryRequirements"));
                return re;
            }
        }) ;
return list;

    }
}
