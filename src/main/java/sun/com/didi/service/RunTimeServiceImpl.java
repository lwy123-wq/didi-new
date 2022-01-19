package sun.com.didi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.com.didi.dao.RunTimeDao;
import sun.com.didi.entity.Recruit;
import sun.com.didi.entity.Report;

@Service
public class RunTimeServiceImpl {
    @Autowired
    private RunTimeDao runTimeDao;

    public Report runtime(String name){
        return runTimeDao.runtime(name);
    }
    public int update(String time,String user){
        return runTimeDao.update(time,user);
    }

    /*续约*/
    public int Insert(String UTCTime){
        return runTimeDao.insertime(new Report(UTCTime));
    }

    public Report select(String name){
        return runTimeDao.selectCompany(name);
    }
    public Report selectUser(String name){
        return runTimeDao.selectUser(name);
    }
}
