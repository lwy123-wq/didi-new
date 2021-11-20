package sun.com.didi.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import sun.com.didi.entity.Recruit;
import sun.com.didi.service.RecruitServiceImpl;

import javax.annotation.Resource;


@RestController
public class UnitController {
    @Resource
    private RecruitServiceImpl unitService;
  @RequestMapping(value = "/Unit",method = RequestMethod.GET)
    public String Employer(){
      return "/Unit";
  }
    @PostMapping(value = "/unit_Employer")
    @ResponseBody
    @Cacheable(cacheNames = "Unit",key = "#Rec_company+'-'+#Rec_logo+'-'+#Rec_category+'-'+#Rec_salary+'-'+#Rec_Duration+'-'+#Rec_experience")
    public String Unit(String Rec_company,String Rec_logo,String Rec_category,String Rec_salary,String Rec_Duration,String Rec_experience){
        Recruit unit =unitService.findByCompany(Rec_company,Rec_logo);
        if (unit.getRec_logo()==null||unit.getRec_company()==null){
            return "该公司或公司logo已注册！";
        }
        unitService.insert(Rec_company,Rec_logo,Rec_category,Rec_salary,Rec_Duration,Rec_experience);
            return "数据添加成功！";
    }
}
