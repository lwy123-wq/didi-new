package sun.com.didi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.com.didi.dao.RecruitDao;
import sun.com.didi.entity.Requirement;

import java.util.List;

@Service
public class SelectRecruitment {
    @Autowired
    private RecruitDao recruitDao;
    public List<Requirement> selectrecruitment(){
        return recruitDao.Select();
    }
}
