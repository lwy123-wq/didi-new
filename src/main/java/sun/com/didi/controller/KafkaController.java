package sun.com.didi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.com.didi.entity.Recruit;
import sun.com.didi.kafka.Producer;

/**
 * 目前没有调用不想删
 */
@Controller
public class KafkaController {

  /*  @Autowired
    private Producer producer;
    @PatchMapping(value = "/producer")
    @ResponseBody
    public void KafkaSend(String category,String salary,String duration){
        Recruit recruit=new Recruit(category,salary,duration);
        producer.producer(recruit);
    }*/
}
