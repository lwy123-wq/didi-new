package sun.com.didi.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.com.didi.entity.Recruit;
import sun.com.didi.service.RecruitServiceImpl;
import sun.com.didi.util.CookieUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
public class RecruitController {
    @Resource
    private RecruitServiceImpl unitService;

    @RequestMapping(value = "/employment", method = RequestMethod.GET)
    public String employment(){
        return "employment";
    }

    @PostMapping(value = "/Recruit")
    @ResponseBody
    @Cacheable(cacheNames = "Recruit",key = "#Rec_company+'-'+#Rec_logo+'-'+#Rec_category+'-'+#Rec_salary+'-'+#Rec_Duration+'-'+#Rec_experience")
    public String Recruit(HttpServletRequest request, HttpServletResponse response,String Rec_company, String Rec_category, String Rec_salary, String Rec_Duration, String Rec_experience){
        Recruit unit =unitService.findByCompany(Rec_company);
        int expire = 60 * 60 * 24 * 7;  //表示7天
        if (unit.getRec_company()==null){
            unitService.insert(Rec_company, Rec_category, Rec_salary, Rec_Duration, Rec_experience);
            CookieUtil.setCookie(request, response, "company",unit.getRec_company(), expire);
            return "数据添加成功！";

        }
        return "该公司或公司logo已注册！";

    }
    @PostMapping(value = "/selectRecruit")
    @ResponseBody
    public List<Recruit> selectRecruit(){
        return unitService.select();
    }
}
