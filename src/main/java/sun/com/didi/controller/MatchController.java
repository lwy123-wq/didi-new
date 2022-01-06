package sun.com.didi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.com.didi.entity.Recruit;
import sun.com.didi.service.IntentionImpl;
import sun.com.didi.service.RecruitServiceImpl;
import sun.com.didi.util.CookieUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

@Controller
public class MatchController {
    public String ca;
    public String pr;
    public String co;
    static ConcurrentLinkedQueue queue=new ConcurrentLinkedQueue();
    static ConcurrentLinkedQueue queue1=new ConcurrentLinkedQueue();
    @Autowired
    private RecruitServiceImpl recruitService;

    @Resource
    private IntentionImpl intention;

    @RequestMapping(value = "/matchsuccess", method = RequestMethod.GET)
    public String match1(){
        return "matchsuccess";
    }
    @PostMapping(value = "/match")
    @ResponseBody
    public List<List<Recruit>> match(HttpServletRequest request){

        Map<String, String> map = CookieUtil.getCookies(request);
        String username = map.get("username");
        queue.add(username);
        List<String> ss=recruitService.surplus(ca,pr,co);
        List<List<Recruit>> arrayList=new ArrayList<>();
        if(ss!=null){
            for(String str:ss){
                if(str!=null) {
                    List<Recruit> list = recruitService.FindByJob(str, ca, pr, co);
                    arrayList.add(list);
                }
            }
        }
        queue.poll();
        return arrayList;

    }
    @RequestMapping(value = "/qiuzhi", method = RequestMethod.GET)
    public String qiuzhi(){
        return "qiuzhi";
    }
    @PostMapping(value = "/jobwanted")
    @ResponseBody
    @Cacheable(cacheNames = "JobWanted",key = "#post+'-'+#category+'-'+#province+'-'+#city+'-'+#condition+'-'+#duration+'-'+#experience")
    public String jobwanted(String post,String category,String province,String city,String condition,String duration,String experience){
        ca=category;
        pr=province;
        co=condition;
        int u=intention.jobwanted(post, category, province, city,condition, duration,experience);
        if (u!=0){
            return "success";
        }

        return "error";

    }
    @PostMapping(value = "/success")
    @ResponseBody
    public String MatchSuccess(String company,HttpServletRequest request){
        Map<String, String> map = CookieUtil.getCookies(request);
        String username = map.get("username");
        queue1.add(username);
        Recruit aa=recruitService.FindByCompany(company);
        String bb=aa.getRec_job();
        int cc=Integer.parseInt(bb);
        int dd=cc-1;
        String ff=Integer.toString(dd);
        if(recruitService.update(ff,company)==1){
            return "success";
        }else {
            return "error";
        }
    }


}