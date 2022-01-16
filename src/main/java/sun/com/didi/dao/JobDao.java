package sun.com.didi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import sun.com.didi.entity.Report;
import sun.com.didi.service.JobService;

import java.util.List;

@Service
public class JobDao implements JobService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public int create(String name, String phone, String id_code, String card, String school,
                      String email, String marriage, String address,String city, Integer years, String education) {
        return jdbcTemplate.update("insert into jobinfo(jobname,phone,id_code,card,school,email,marriage,address,city,years,education)values(?,?,?,?,?,?,?,?,?,?,?)",
                name,phone,id_code,card,school,email,marriage,address,city,years,education);

    }
    @Override
    public int insertReport(String company,String username,String time){
        String sql="insert into Report(company,user,Time)values (?,?,?)";
        return jdbcTemplate.update(sql,company,username,time);
    }

}
