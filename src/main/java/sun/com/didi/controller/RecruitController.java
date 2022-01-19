package sun.com.didi.controller;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.com.didi.entity.Recruit;
import sun.com.didi.filter.BloomFilter;
import sun.com.didi.service.BloomFilterService;
import sun.com.didi.service.RecruitServiceImpl;
import sun.com.didi.util.CookieUtil;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.Map;


@Controller
public class RecruitController {
    @Resource
    private RecruitServiceImpl unitService;
    @Resource
    private BloomFilter bloomFilter;
    @Autowired
    private BloomFilterService bloomFilterService;

    @RequestMapping(value = "/employment", method = RequestMethod.GET)
    public String employment(){
        return "employment";
    }

    @PostMapping(value = "/Recruit")
    @ResponseBody
    @Cacheable(cacheNames = "Recruit",key = "#Rec_company+'-'+#Rec_logo+'-'+#Rec_category+'-'+#Rec_salary+'-'+#Rec_Duration+'-'+#Rec_experience")
    public String Recruit(HttpServletRequest request, HttpServletResponse response, String Rec_company, String Rec_logo, String Rec_address,String Rec_job, String Rec_category, String Rec_salary, String Rec_Duration,String UTCTime, String Rec_experience) throws Exception {
        Recruit unit =unitService.findByCompany(Rec_company);
        int expire = 60 * 60 * 24 * 7;  //表示7天
        if (unit.getRec_company()==null){
            int insert = unitService.insert(Rec_company, Rec_logo,Rec_address, Rec_job,Rec_category, Rec_salary, Rec_Duration,UTCTime, Rec_experience);
            CookieUtil.setCookie(request, response, "company",unit.getRec_company(), expire);
            try {
                bloomFilterService.addByBloomFilter(bloomFilter,Rec_company,Rec_company);
                bloomFilterService.addByBloomFilter(bloomFilter,Rec_category,Rec_category);
            } catch (Exception e) {
                e.printStackTrace();
                return "添加失败";
            }
            if (insert>0){
                return "数据添加成功!";
            }
        }
        return "该公司或公司logo已注册!";

    }

    @PostMapping(value = "/selectRecruit")
    @ResponseBody
    public List<Recruit> selectRecruit(){
        return unitService.select();
    }

    @PostMapping(value = "/companyInformation")
    @ResponseBody
    public Recruit selectInformation(HttpServletRequest request){
        Map<String, String> map = CookieUtil.getCookies(request);
        String username = map.get("username");
        Recruit recruit=unitService.showMatchCompany(username);
        return recruit;
    }
}
