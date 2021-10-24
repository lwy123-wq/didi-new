package sun.com.didi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sun.com.didi.entity.Picture;
import sun.com.didi.service.PictureService;

import java.util.Date;

@Repository
public class PictureDao implements PictureService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insert(Date time, String fileName) {
        String sql="insert int picture(time,filename)values(?,?)";
        return jdbcTemplate.update(sql,time,fileName);
    }

    @Override
    public Picture update(Date time, String fileName) {
        return null;
    }
}
