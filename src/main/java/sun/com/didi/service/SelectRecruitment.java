package sun.com.didi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.com.didi.dao.RecruitDao;
import sun.com.didi.entity.Recruit;

import java.util.List;

@Service
public class SelectRecruitment {
    @Autowired
    private RecruitDao recruitDao;
    public List<Recruit> selectrecruitment(){
        return recruitDao.Select();
    }
}
