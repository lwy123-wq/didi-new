package com.example.didiwork;

import com.example.didiwork.didi.dao.RecruitDao;
import com.example.didiwork.didi.entity.Requirement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DidiWorkApplicationTests {

    @Autowired
    private RecruitDao recruitDao;
    @Test
    void contextLoads() {
        Requirement requirement = recruitDao.SelectRecruit(1);
        System.out.println(requirement.getCompany()+requirement.getSalaryRequirements()+requirement.getOccupationalCategory()+requirement.getRemainingPositions());

    }

}
