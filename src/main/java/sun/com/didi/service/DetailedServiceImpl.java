package sun.com.didi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.com.didi.dao.DetailedDao;
import sun.com.didi.dao.JobDao;
import sun.com.didi.entity.Detailed;
import sun.com.didi.entity.Recruit;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetailedServiceImpl {
    @Autowired
    private DetailedDao detailedDao;
    public int insertDetailed(String company,String Information){
        return detailedDao.DetailedInsert(new Detailed(company,Information)) ;
    }

    public ArrayList<Detailed> SelectDetailed(String company){
        return detailedDao.SelectDetailed(company);
    }
}

