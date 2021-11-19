package sun.com.didi.service;

import org.springframework.beans.factory.annotation.Autowired;
import sun.com.didi.dao.unit_Dao;
import sun.com.didi.entity.Login;
import sun.com.didi.entity.Unit;

public class unitServiceImpl {
    @Autowired
    private unit_Dao unit_dao;
    public int insert(String Rec_company,String Rec_logo,String Rec_category,int Rec_salary,int Rec_Duration,String Rec_experience){

        return unit_dao.unit_insert(new Unit(Rec_company,Rec_logo,Rec_category,Rec_salary,Rec_Duration,Rec_experience));
    }
}
