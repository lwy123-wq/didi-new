package sun.com.didi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.com.didi.entity.Requirement;
import sun.com.didi.service.WorkServiceImpl;

@Controller
public class WorkController {

    @Autowired
    private WorkServiceImpl workService;

    @RequestMapping(value = "/Recruit", method = RequestMethod.GET)
    public String start(){
        return "Recruit";
    }
    @PostMapping(value = "")
    @ResponseBody
    public String  Recruitment(int uid){
        Requirement user =workService.selectWork(uid);
        if(user == null){
            return "Y";
        }
        return "N";
    }
}
