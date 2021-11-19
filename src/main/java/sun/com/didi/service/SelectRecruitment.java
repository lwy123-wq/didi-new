package sun.com.didi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.com.didi.dao.RecruitDao;
import sun.com.didi.entity.Requirement;
@Service
public class SelectRecruitment {
    @Autowired
    private RecruitDao recruitDao;
    public Requirement selectrecruitment(){
        return recruitDao.Select();
    }
}
