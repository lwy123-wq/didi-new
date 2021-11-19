package sun.com.didi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sun.com.didi.entity.Unit;

@Repository
public class unit_Dao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public int unit_insert(Unit unit_user){

        String sql="INSERT INTO unit_user (name,password,email)VALUES(?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, unit_user.getRec_category(), unit_user.getRec_company(),
                unit_user.getRec_Duration(),unit_user.getRec_logo(),unit_user.getRec_salary(),unit_user.getRec_logo());
    }

}
