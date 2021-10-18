package sun.com.didi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sun.com.didi.entity.Intention;

@Repository
public class IntentionDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int InsertIntention(Intention intention){
        String sql="insert into intention(post,category,province,city,i_condition,duration,experience)VALUES(?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, intention.getPost(),intention.getCategory(),intention.getProvince(),intention.getCity(),intention.getCondition(),intention.getDuration(),intention.getExperience());
    }

}
