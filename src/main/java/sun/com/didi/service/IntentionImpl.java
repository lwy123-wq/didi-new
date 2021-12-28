package sun.com.didi.service;


import org.springframework.stereotype.Service;
import sun.com.didi.dao.IntentionDao;
import sun.com.didi.entity.Intention;

import javax.annotation.Resource;

@Service
public class IntentionImpl {
    @Resource
    private IntentionDao intentionDao;
    public int jobwanted(String post,String category,String city, String province, String condition, String duration, String experience){
        return intentionDao.InsertIntention(new Intention( post,city,category , province, condition, duration,experience));
    }
}
