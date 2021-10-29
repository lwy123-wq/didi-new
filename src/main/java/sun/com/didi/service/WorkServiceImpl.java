package sun.com.didi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.com.didi.dao.RecruitDao;
import sun.com.didi.entity.Requirement;

@Service
public class WorkServiceImpl {
    @Autowired
    private RecruitDao recruitDao;
    public Requirement selectWork(int uid){
        return recruitDao.SelectRecruit(uid);
    }
}
