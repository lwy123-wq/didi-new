package sun.com.didi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.com.didi.entity.JobInfo;
import sun.com.didi.entity.Login;
import sun.com.didi.entity.Recruit;
import sun.com.didi.service.*;
import sun.com.didi.util.CookieUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

@Controller
public class MatchController {
    final String s="";
    public String ca;
    public String pr;
    public String co;
    public String company1;
    public  String username;
    static ConcurrentLinkedQueue queue=new ConcurrentLinkedQueue();
    static ConcurrentLinkedQueue queue1=new ConcurrentLinkedQueue();
    @Autowired
    private RecruitServiceImpl recruitService;
    @Autowired
    private UserServiceImpl userService;
    @Resource
    private IntentionImpl intention;
    @Resource
    private IEmailService emailService;
    @Autowired
    private JobServiceImpl jobService;
    @RequestMapping(value = "/matchsuccess", method = RequestMethod.GET)
    public String match1(){
        return "matchsuccess";
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String order(){
        return "order";
    }

    @RequestMapping(value = "/aaa", method = RequestMethod.GET)
    public String order1(){
        return "aaa";
    }


    @RequestMapping(value = "/showdetail", method = RequestMethod.GET)
    public String show(){
        return "showdetails";
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
                }else {
                    Login email = userService.findEmail(username);
                     sendEmail(email.getEmail(), s);
                }
            }
        }
        queue.poll();
        return arrayList;
    }
    public boolean sendEmail(String to,String contentText){
        return emailService.sendAttachmentMail(to,contentText);

    }
    @RequestMapping(value = "/qiuzhi", method = RequestMethod.GET)
    public String qiuzhi(){
        return "qiuzhi";
    }
    @PostMapping(value = "/jobwanted")
    @ResponseBody
    //@Cacheable(cacheNames = "JobWanted",key = "#post+'-'+#category+'-'+#province+'-'+#city+'-'+#condition+'-'+#duration+'-'+#experience")
    public String jobwanted(String post,String category,String province,String city,String condition,String duration,String experience){
        ca=category;
        pr=province;
        co=condition;
        System.out.println(category+province+condition+"aaaaaaaaaaaaaaaaa");
        int u=intention.jobwanted(post, category, province, city,condition, duration,experience);
        if (u!=0){
            return "success";
        }

        return "error";

    }
    @PostMapping(value = "/success")
    @ResponseBody
    public String MatchSuccess(@RequestBody String company, HttpServletRequest request) throws UnsupportedEncodingException {
        //jobService.insertReport()
        company1=company;
        String s = URLDecoder.decode(company,"UTF-8");
        String stri[] = s.split("=");
        String query = stri[1];
        Map<String, String> map = CookieUtil.getCookies(request);
        username = map.get("username");
        queue1.add(username);
        Recruit aa=recruitService.FindByCompany(query);
        String bb=aa.getRec_job();
        int cc=Integer.parseInt(bb);
        int dd=cc-1;
        String ff=Integer.toString(dd);
        if(recruitService.update(ff,query)==1){
            queue1.poll();

            return "success";
        }else {
            return "error";
        }
    }

    @RequestMapping(value = "/showMatchCompany")
    @ResponseBody
    public List showMatchCompany(){

        List<Recruit> list = recruitService.showMatchCompany(company1);

        return list;
    }

    @RequestMapping(value = "/showMatchPerson")
    @ResponseBody
    public List showMatchPerson(){

        List<JobInfo> list = recruitService.showMatchPerson(username);

        return list;
    }
    @PostMapping(value = "/sendToken")
    @ResponseBody
    public String sendToken(HttpServletRequest request){
        Map<String, String> map = CookieUtil.getCookies(request);
        username = map.get("username");
        Login core=userService.selectToken(username);
        String core1=core.getCode();
        return core1;
    }


}