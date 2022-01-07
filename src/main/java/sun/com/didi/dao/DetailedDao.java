package sun.com.didi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import sun.com.didi.entity.Detailed;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DetailedDao {
@Autowired
    JdbcTemplate jdbcTemplate;

public List<Detailed> SelectDetailed(){
    String sql= "SELECT * FROM Detailed";
    List<Detailed> list = jdbcTemplate.query(sql, new RowMapper<Detailed>() {
        @Override
        public Detailed mapRow(ResultSet rs, int rowNum) throws SQLException {
           Detailed detailed=new Detailed();
           detailed.setCompany(rs.getString("company"));
           detailed.setInformation(rs.getString("Information"));
            return detailed;
        }
    }) ;
    return list;
}

public int DetailedInsert(Detailed detailed){
    String sql="INSERT INTO Detailed (company,Infotmation)VALUES(?,?)";
    return jdbcTemplate.update(sql, detailed.getCompany(),detailed.getInformation());
}
}
