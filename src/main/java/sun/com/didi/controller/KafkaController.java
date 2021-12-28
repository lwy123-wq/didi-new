package sun.com.didi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import sun.com.didi.entity.Recruit;
import sun.com.didi.kafka.Producer;

@Controller
public class KafkaController {
    @Autowired
    private Producer producer;
    public String KafkaSend(String category,String salary,String duration){
        Recruit recruit=new Recruit(category,salary,duration);
        return null;

    }
}
