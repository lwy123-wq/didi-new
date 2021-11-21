package sun.com.didi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.com.didi.dao.RecruitDao;
import sun.com.didi.entity.Recruit;

@Service
public class RecruitServiceImpl {
    @Autowired
    private RecruitDao recruitDao;


    public Recruit findByCompany(String username) {
        return recruitDao.findByRecruit(username);
    }
    public int insert(String Rec_company,String Rec_category,String Rec_salary,String Rec_Duration,String Rec_experience){

        return recruitDao.RecruitInsert(new Recruit(Rec_company,Rec_category,Rec_salary,Rec_Duration,Rec_experience));
    }
}