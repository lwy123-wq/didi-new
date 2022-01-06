package sun.com.didi.controller;

import jdk.nashorn.internal.ir.RuntimeNode;
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
    @Autowired
    private RecruitServiceImpl recruitService;

    @Resource
    private IntentionImpl intention;

    @PostMapping(value = "/match")
    @ResponseBody
    public ArrayList<ArrayList<Recruit>> match(HttpServletRequest request){
        System.out.println(ca+"aaaaaaaaaaaaaaaaaaa");

        Map<String, String> map = CookieUtil.getCookies(request);
        String username = map.get("username");
        queue.add(username);
        List<String> ss=recruitService.surplus(ca,pr,co);
        ArrayList<ArrayList<Recruit>> arrayList=new ArrayList();
        if(ss!=null){
            for(String str:ss){
                ArrayList<Recruit> list= (ArrayList<Recruit>) recruitService.FindByJob(str);
                arrayList.add(list);
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
}