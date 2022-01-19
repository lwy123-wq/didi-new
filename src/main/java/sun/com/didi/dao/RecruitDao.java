package sun.com.didi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sun.com.didi.entity.JobInfo;
import sun.com.didi.entity.Recruit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RecruitDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Recruit> Select(){
        String sql= "SELECT * FROM Recruit";
        List<Recruit> list = jdbcTemplate.query(sql, new RowMapper<Recruit>() {
            @Override
            public Recruit mapRow(ResultSet rs, int rowNum) throws SQLException {
                Recruit re = new Recruit();
                re.setRec_company(rs.getString("Rec_company"));
                re.setRec_logo(rs.getString("Rec_logo"));
                re.setRec_address(rs.getString("Rec_address"));
                re.setRec_job(rs.getString("Rec_job"));
                re.setRec_category(rs.getString("Rec_category"));
                re.setRec_salary(rs.getString("Rec_salary"));
                re.setRec_Duration(rs.getString("Rec_Duration"));
                re.setUTCTime(rs.getString("UTCTime"));
                re.setRec_experience(rs.getString("Rec_experience"));

                return re;
            }
        }) ;
        return list;

    }


    public int RecruitInsert(Recruit recruit){

        String sql="INSERT INTO Recruit (Rec_company,Rec_logo,Rec_address,Rec_job,Rec_category,Rec_salary,Rec_Duration,UTCTime,Rec_experience)VALUES(?,?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, recruit.getRec_company(),recruit.getRec_logo(),recruit.getRec_address(),recruit.getRec_job(), recruit.getRec_category(),recruit.getRec_salary(),
                recruit.getRec_Duration(),recruit.getUTCTime(),recruit.getRec_experience());
    }

    public Recruit findByRecruit(String name){
        final Recruit unit=new Recruit();
        String sql = "SELECT * FROM Recruit WHERE Rec_company=?";
        jdbcTemplate.query(sql, new Object[]{name}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                unit.setRec_company(rs.getString(2));
            }
        });
        return unit;
    }
    public ArrayList<Recruit> findCompany(){
        final Recruit unit=new Recruit();
        String sql = "SELECT * FROM Recruit";
        ArrayList<Recruit> list = (ArrayList<Recruit>) jdbcTemplate.query(sql, new RowMapper<Recruit>() {
            @Override
            public Recruit mapRow(ResultSet rs, int rowNum) throws SQLException {
                Recruit re = new Recruit();
                re.setRec_company(rs.getString("Rec_company"));
                return re;
            }
        });
        return list;
    }

    public ArrayList<Recruit> findByCategory(){
        final Recruit unit=new Recruit();
        String sql = "SELECT * FROM Recruit";
        ArrayList<Recruit> list = (ArrayList<Recruit>) jdbcTemplate.query(sql, new RowMapper<Recruit>() {
            @Override
            public Recruit mapRow(ResultSet rs, int rowNum) throws SQLException {
                Recruit re = new Recruit();
                re.setRec_category(rs.getString("Rec_category"));
                return re;
            }
        });
        return list;
    }
    //匹配
    public List<String> surplus(String category, String province, String condition){
        String sql="SELECT Rec_job From Recruit WHERE Rec_category=? and Rec_address='%'+?+'%' and Rec_salary=? ";
        List<String> list=jdbcTemplate.query(sql, new Object[]{category, province, condition}, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                Recruit recruit1=new Recruit();
                String aa=recruit1.setRec_job(rs.getString("Rec_job"));
                return aa;
            }
        });


        return list;
    }
    public List<Recruit> findByJob(String Rec_job,String category, String province, String condition){
        String sql = "SELECT * FROM Recruit where Rec_job=? AND Rec_category=? and Rec_address='%'+?+'%' and Rec_salary=?";
        List<Recruit> list1 = jdbcTemplate.query(sql, new Object[]{Rec_job,category, province, condition},new RowMapper<Recruit>() {
            @Override
            public Recruit mapRow(ResultSet rs, int rowNum) throws SQLException {
                Recruit re = new Recruit();
                re.setRec_company(rs.getString("Rec_company"));
                re.setRec_logo(rs.getString("Rec_logo"));
                re.setRec_address(rs.getString("Rec_address"));
                re.setRec_job(rs.getString("Rec_job"));
                re.setRec_category(rs.getString("Rec_category"));
                re.setRec_salary(rs.getString("Rec_salary"));
                re.setRec_Duration(rs.getString("Rec_Duration"));
                re.setUTCTime(rs.getString("UTCTime"));
                re.setRec_experience(rs.getString("Rec_experience"));
                return re;
            }
        });
        return list1;
    }

    public Recruit findByCompany(String company){
        String sql="select Rec_job from Recruit where Rec_company=?";
        Recruit recruit=new Recruit();
        jdbcTemplate.query(sql, new Object[]{company}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                recruit.setRec_job(rs.getString(1));
            }
        });
        return recruit;
    }

    public int update(String job, String company){
        String sql="UPDATE Recruit SET Rec_job=? where Rec_company=?";
        return jdbcTemplate.update(sql,job,company);

    }

    public Recruit showMatchCompany(String Rec_company){
        String sql = "Select * FROM Recruit WHERE Rec_company=?";
        Recruit re = new Recruit();
        jdbcTemplate.query(sql, new Object[]{Rec_company}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {

                re.setRec_company(rs.getString(2));
                re.setRec_logo(rs.getString(3));
                re.setRec_address(rs.getString(4));
                re.setRec_job(rs.getString(5));
                re.setRec_category(rs.getString(6));
                re.setRec_salary(rs.getString(7));
                re.setRec_Duration(rs.getString(8));
                re.setUTCTime(rs.getString(9));
                re.setRec_experience(rs.getString(10));
            }
        });

        return re;
    }

    public  JobInfo showMatchPerson(String jobName){
        String sql = "Select * FROM jobinfo WHERE jobname=?";
        JobInfo jobInfo = new JobInfo();
        jdbcTemplate.query(sql, new Object[]{jobName}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {

                jobInfo.setName(rs.getString(2));
                jobInfo.setPhone(rs.getString(3));
                jobInfo.setId_code(rs.getString(4));
                jobInfo.setCard(rs.getString(5));
                jobInfo.setSchool(rs.getString(6));
                jobInfo.setEmail(rs.getString(7));
                jobInfo.setMarriage(rs.getString(8));
                jobInfo.setAddress(rs.getString(9));
                jobInfo.setEducation(rs.getString(10));
                jobInfo.setCity(rs.getString(11));

            }
        });
        return jobInfo;
    }
     public Recruit selectTime(String company){
        String sql="select UTCTime from Recruit where Rec_company=?";
        Recruit recruit=new Recruit();
        jdbcTemplate.query(sql, new Object[]{company}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                recruit.setUTCTime(rs.getString(1));
            }
        });
        return recruit;
     }

}
