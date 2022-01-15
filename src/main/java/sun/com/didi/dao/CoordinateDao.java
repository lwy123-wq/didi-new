package sun.com.didi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
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
        String sql= "SELECT longitude, latitude FROM coordinate";
        List<Coordinate> list = jdbcTemplate.query(sql, new RowMapper<Coordinate>() {
            @Override
            public Coordinate mapRow(ResultSet rs, int rowNum) throws SQLException {
                Coordinate re = new Coordinate();
                re.setLongitude(rs.getDouble("longitude"));
                re.setLatitude(rs.getDouble("latitude"));

                return re;
            }
        }) ;
        return list;

    }

    public Coordinate selectCoordinate(double lon,double lat){
        String sql="select company from coordinate where longitude=? and latitude=?";
        Coordinate coordinate=new Coordinate();
        jdbcTemplate.query(sql, new Object[]{lon,lat}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                 coordinate.setCompanyName(rs.getString(1));
            }
        });
        return coordinate;
    }

}
