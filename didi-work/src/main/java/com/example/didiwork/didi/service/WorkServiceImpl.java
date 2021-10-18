package com.example.didiwork.didi.service;

import com.example.didiwork.didi.dao.RecruitDao;
import com.example.didiwork.didi.entity.Requirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkServiceImpl {
    @Autowired
    private RecruitDao recruitDao;
    public Requirement selectWork(int uid){
        return recruitDao.SelectRecruit(uid);
    }
}
