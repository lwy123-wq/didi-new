package sun.com.didi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sun.com.didi.entity.Recruit;

import java.sql.ResultSet;
import java.sql.SQLException;
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

        String sql="INSERT INTO Recruit (Rec_company,Rec_logo,Rec_category,Rec_salary,Rec_Duration,Rec_experience)VALUES(?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, recruit.getRec_company(), recruit.getRec_logo(),recruit.getRec_category(),recruit.getRec_salary(),
                recruit.getRec_Duration(),recruit.getRec_experience());
    }

    public Recruit findByRecruit(String name, String logo){
        final Recruit unit=new Recruit();
        String sql = "SELECT * FROM Login WHERE Rec_company=? AND Rec_logo";
        jdbcTemplate.query(sql, new Object[]{name,logo}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                unit.setRec_company(rs.getString(2));
                unit.setRec_logo(rs.getString(4));
            }
        });
        return unit;
    }
}
