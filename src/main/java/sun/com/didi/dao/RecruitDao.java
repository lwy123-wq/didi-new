package sun.com.didi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
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
                re.setRec_experience(rs.getString("Rec_experience"));

                return re;
            }
        }) ;
        return list;

    }


    public int RecruitInsert(Recruit recruit){

        String sql="INSERT INTO Recruit (Rec_company,Rec_logo,Rec_address,Rec_job,Rec_category,Rec_salary,Rec_Duration,Rec_experience)VALUES(?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, recruit.getRec_company(),recruit.getRec_logo(),recruit.getRec_address(),recruit.getRec_job(),recruit.getRec_salary(), recruit.getRec_category(),
                recruit.getRec_Duration(),recruit.getRec_experience());
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
        String sql="select Rec_job from Recruit where Rec_category=? and Rec_address=? and Rec_salary=? ";
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
    public ArrayList<Recruit> findByJob(String Rec_job){
        String sql = "SELECT * FROM Recruit where Rec_job=? ";
        ArrayList<Recruit> list = (ArrayList<Recruit>) jdbcTemplate.query(sql, new Object[]{Rec_job},new RowMapper<Recruit>() {
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
                re.setRec_experience(rs.getString("Rec_experience"));
                return re;
            }
        });
        return list;
    }
}
