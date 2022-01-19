package sun.com.didi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.com.didi.entity.JobInfo;
import sun.com.didi.service.JobServiceImpl;
import sun.com.didi.util.CookieUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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
    @Cacheable(cacheNames = "Information",key = "#name+'-'+#phone+'-'+#id_code+'-'+#card+'-'+#school+'-'+#email+'-'+#marriage+'-'+#address+'-'+#city+'-'+#education")
    public String Information(String name,String phone,String id_code,String card,String school,String email,String marriage,String address,String city,String education){
        int i = jobService.insertJob(name, phone, id_code, card, school, email, marriage, address, city, education);
        if (i!=0){
            return "success";
        }
        return "error";
    }
    @RequestMapping(value = "/chat",method = RequestMethod.GET)
    public String chat(){
        return "feedback";
    }

    @RequestMapping(value = "/SingleChat",method = RequestMethod.GET)
    public String single(Model model, HttpServletRequest request){
        Map<String, String> map = CookieUtil.getCookies(request);
        String username = map.get("username");
        String company=map.get("company");
        model.addAttribute("username",username);
        model.addAttribute("company",company);
        return "chat";
    }

    /*展示个人信息*/
    @PostMapping(value = "/JobQuery")
    @ResponseBody
    public List<JobInfo> SelectJob(HttpServletRequest request){
        Map<String, String> map = CookieUtil.getCookies(request);
        String username = map.get("username");
        List<JobInfo> select = jobService.select(username);
        return select;
    }
}
