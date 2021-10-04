package sun.com.did.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.com.did.service.JobServiceImpl;

public class JobController {
    @Autowired
    private JobServiceImpl jobService;
    @RequestMapping(value = "/Insert",method = RequestMethod.GET)
    public String start(){
        return "Insert";
    }
    @PostMapping(value = "/Insertly")
    @ResponseBody
    public String Information(String name,String phone,String id_code,String card,String school,String email,String marriage,String address,int years,String education){
        int i = jobService.insertJob(name, phone, id_code, card, school, email, marriage, address, years, education);
        if (i!=0){
            return "添加成功！";
        }
        return "添加失败！";
    }
}
