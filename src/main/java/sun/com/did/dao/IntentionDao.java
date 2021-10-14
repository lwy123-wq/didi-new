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
        String sql="insert into intention(port,city,category,province,i_condition,duration,experience)VALUES(?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, intention.getPort(),intention.getCity(),intention.getCategory(),intention.getProvince(),intention.getCondition(),intention.getDuration(),intention.getExperience());
    }

}
