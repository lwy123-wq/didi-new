package sun.com.didi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.com.didi.dao.RunTimeDao;
import sun.com.didi.entity.Recruit;

@Service
public class RunTimeServiceImpl {
    @Autowired
    private RunTimeDao runTimeDao;

    public Recruit runtime(String company){
        return runTimeDao.runtime(company);
    }
    public int update(String time,String company){
        return runTimeDao.update(time,company);
    }

    public int Insert(String UTCTime){
        return runTimeDao.insertime(new Recruit(UTCTime));
    }
}
