package sun.com.didi.service;

import jdk.internal.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.com.didi.dao.CoordinateDao;
import sun.com.didi.entity.Coordinate;

import java.util.List;

@Service
public class CoordinateService {
    @Autowired
    private CoordinateDao coordinateDao;
    public int insertCoordinate(double lon, double lat, String companyName){
        return coordinateDao.insertCoordinate(lon,lat,companyName);
    }
    public List<Coordinate> select(){
        return coordinateDao.selectNode();
    }
    public Coordinate selectCoordinate(double lon,double lat){
        return coordinateDao.selectCoordinate(lon,lat);
    }
}
