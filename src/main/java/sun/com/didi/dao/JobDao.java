package sun.com.didi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import sun.com.didi.entity.JobInfo;
import sun.com.didi.entity.Report;
import sun.com.didi.service.JobService;

import java.sql.ResultSet;
import java.sql.SQLException;
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

    public List<JobInfo> SelectJob(String name){
        String sql = "SELECT * FROM jobinfo where name=?";
        List<JobInfo> list = jdbcTemplate.query(sql, new Object[]{name},new RowMapper<JobInfo>() {
            @Override
            public JobInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                JobInfo job = new JobInfo();
                job.setName(rs.getString("jobname"));
                job.setPhone(rs.getString("phone"));
                job.setId_code(rs.getString("id_code"));
                job.setSchool(rs.getString("school"));
                job.setEmail(rs.getString("email"));
                job.setEducation(rs.getString("education"));
                job.setCard(rs.getString("card"));
                job.setMarriage(rs.getString("marriage"));
                job.setAddress(rs.getString("address"));
                job.setCity(rs.getString("city"));
                return job;
            }
        });
        return list;

    }

}
