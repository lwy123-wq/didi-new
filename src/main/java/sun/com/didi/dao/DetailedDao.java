package sun.com.didi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import sun.com.didi.entity.Detailed;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DetailedDao {
@Autowired
    JdbcTemplate jdbcTemplate;

public ArrayList<Detailed> SelectDetailed(String company){
  final Detailed detailed=new Detailed();
    ArrayList<Detailed> arrayList=new ArrayList<>();
    String sql= "SELECT * FROM Detailed WHERE company =?";
    jdbcTemplate.query(sql, new Object[]{company}, new RowCallbackHandler() {
        @Override
        public void processRow(ResultSet resultSet) throws SQLException {
            detailed.setInformation(resultSet.getString(3));
            arrayList.add(detailed);
        }
    });
    return arrayList;
}

public int DetailedInsert(Detailed detailed){
    String sql="INSERT INTO Detailed (company,Information)VALUES(?,?)";
    return jdbcTemplate.update(sql, detailed.getCompany(),detailed.getInformation());
}
}
