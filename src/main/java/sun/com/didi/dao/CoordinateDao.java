package sun.com.didi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sun.com.didi.entity.Coordinate;
import sun.com.didi.entity.Recruit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CoordinateDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public int insertCoordinate(double lon, double lat, String companyName){
        String sql="insert into coordinate(longitude,latitude,company)values (?,?,?)";
        return jdbcTemplate.update(sql,lon,lat,companyName);
    }
    public List<Coordinate> selectNode(){
        String sql="select longitude,latitude from coordinate";
        List<Coordinate> list=jdbcTemplate.query(sql, new RowMapper<Coordinate>() {
            Coordinate coordinate=new Coordinate();
            @Override
            public Coordinate mapRow(ResultSet rs, int rowNum) throws SQLException {
                coordinate.setLongitude(rs.getDouble("longitude"));
                coordinate.setLatitude(rs.getDouble("latitude"));
                return coordinate;
            }

        });
        return list;
    }

}
