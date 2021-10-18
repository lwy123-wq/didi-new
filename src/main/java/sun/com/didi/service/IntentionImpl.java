package sun.com.did.service;


import org.springframework.stereotype.Service;
import sun.com.did.dao.IntentionDao;
import sun.com.did.entity.Intention;

import javax.annotation.Resource;

@Service
public class IntentionImpl {
    @Resource
    private IntentionDao intentionDao;
    public int jobwanted(String post,String category,String city, String province,  String condition, String duration, String experience){
        return intentionDao.InsertIntention(new Intention( post,city,category , province, condition, duration,experience));
    }
}
