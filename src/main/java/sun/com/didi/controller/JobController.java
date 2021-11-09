package sun.com.didi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.com.didi.service.JobServiceImpl;

@Controller
public class JobController {
    @Autowired
    private JobServiceImpl jobService;
    @RequestMapping(value = "/information",method = RequestMethod.GET)
    public String start(){
        return "information";
    }
    @PostMapping(value = "/MyInformation")
    @ResponseBody
    @Cacheable(cacheNames = "Information",key = "#name+'-'+#phone+'-'+#id_code+'-'+#card+'-'+#school+'-'+#email+'-'+#marriage+'-'+#address+'-'+#city+'-'+#year+'-'+#education")
    public String Information(String name,String phone,String id_code,String card,String school,String email,String marriage,String address,String city,Integer year,String education){
        int i = jobService.insertJob(name, phone, id_code, card, school, email, marriage, address, city,year, education);
        if (i!=0){
            return "success";
        }
        return "error";
    }
    @RequestMapping(value = "/chat",method = RequestMethod.GET)
    public String chat(){
        return "feedback";
    }
}
