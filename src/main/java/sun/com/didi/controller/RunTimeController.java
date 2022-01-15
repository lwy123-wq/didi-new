package sun.com.didi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.com.didi.entity.Recruit;
import sun.com.didi.service.RunTimeServiceImpl;

import java.net.URLDecoder;

@Controller
public class RunTimeController {
    //签到
    @Autowired
    private RunTimeServiceImpl runTimeService;
    @PostMapping(value = "/OF")
    @ResponseBody
    public String tupleof(@RequestBody String company) throws Exception {
        String s = URLDecoder.decode(company, "UTF-8");
        String stri[] = s.split("=");
        String query = stri[1];
        Recruit runtime = runTimeService.runtime(query);
        String utcTime = runtime.getUTCTime();
        int time = Integer.parseInt(utcTime);
        if (time<0){
            return "sucess";
            /*结算工资*/
        }else {
            int timeplus = time - 1;
            String s1 = Integer.toString(timeplus);
            if (runTimeService.update(s1, query) == 1) {
                return "success";
            }
        }
       return "error";
    }
    @PostMapping(value = "/offtime")
    @ResponseBody
    public String Renewal(@RequestBody String time) throws Exception {
        String s = URLDecoder.decode(time, "UTF-8");
        String stri[] = s.split("=");
        String query = stri[1];
        int insert = runTimeService.Insert(query);
        if (insert>0){
            return "success";
        }else
            return "error";
    }
}
