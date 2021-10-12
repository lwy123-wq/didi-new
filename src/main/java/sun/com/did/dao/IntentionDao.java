package sun.com.did.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sun.com.did.entity.Intention;
import sun.com.did.entity.Login;

@Repository
public class IntentionDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int InsertIntention(Intention intention){
        String sql="INSERT INTO Intention (port,city,Category,province,condition,Duration,experience)VALUES(?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, intention.getPort(),intention.getCategory(),intention.getProvince(),intention.getCity(),intention.getCondition(),intention.getDuration(),intention.getExperience());
    }

}
