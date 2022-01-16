package sun.com.didi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.com.didi.dao.JobDao;

@Service
public class JobServiceImpl extends JobDao {
    @Autowired
    private JobDao jobDao;
    public int insertJob(String name,String phone,String id_code,String card,String school,String email,String marriage,String address,String city,Integer years,String education){
        return jobDao.create(name,phone,id_code,card,school,email,marriage,address,city,years,education) ;
    }

    public int insertReport(String company,String username,String time){
        return jobDao.insertReport(company,username,time);
    }

}
