package sun.com.did.service;


import org.springframework.stereotype.Service;
import sun.com.did.dao.IntentionDao;
import sun.com.did.entity.Intention;

import javax.annotation.Resource;

@Service
public class IntentionImpl {
    @Resource
    private IntentionDao intentionDao;
    public int jobwanted(String port, String Category, String province, String city, String condition, String Duration, String experience){
        return intentionDao.InsertIntention(new Intention( port,Category, province, city, condition, Duration,experience));
    }
}
