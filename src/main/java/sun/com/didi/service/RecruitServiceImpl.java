package sun.com.didi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.com.didi.dao.RecruitDao;
import sun.com.didi.entity.JobInfo;
import sun.com.didi.entity.Recruit;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecruitServiceImpl {
    @Autowired
    private RecruitDao recruitDao;

    public Recruit findByCompany(String username) {
        return recruitDao.findByRecruit(username);
    }
    public ArrayList<Recruit> findCompany(){
        return recruitDao.findCompany();
    }
    public  ArrayList<Recruit> findByCategory() {
        return recruitDao.findByCategory();
    }

    public int insert(String Rec_company, String Rec_logo, String Rec_address,String Rec_job, String Rec_category, String Rec_salary, String Rec_Duration,String UTCTime, String Rec_experience){

        return recruitDao.RecruitInsert(new Recruit(Rec_company,Rec_logo,Rec_address,Rec_job,Rec_category,Rec_salary,Rec_Duration,UTCTime,Rec_experience));
    }
    public List<Recruit> select(){
        return recruitDao.Select();
    }

    public List<String> surplus(String category, String province, String condition){
        return recruitDao.surplus(category,province,condition);

    }
    public List<Recruit> FindByJob(String job,String category, String province,String condition){
        return recruitDao.findByJob(job,category, province, condition);
    }

    public Recruit FindByCompany(String company){
        return recruitDao.findByCompany(company);
    }

    public int update(String job,String company){
        return recruitDao.update(job,company);
    }

    public Recruit showMatchCompany(String Rec_company){
        return recruitDao.showMatchCompany(Rec_company);
    }

    public JobInfo showMatchPerson(String jobName){
        return recruitDao.showMatchPerson(jobName);
    }
    public Recruit selectTime(String company){
        return recruitDao.selectTime(company);
    }

}
