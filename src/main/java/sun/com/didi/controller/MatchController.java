package sun.com.didi.controller;

import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.com.didi.entity.Recruit;
import sun.com.didi.service.RecruitServiceImpl;
import sun.com.didi.util.CookieUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

@Controller
public class MatchController {
    static ConcurrentLinkedQueue queue=new ConcurrentLinkedQueue();
    @Autowired
    private RecruitServiceImpl recruitService;

    @PostMapping(value = "/match")
    @ResponseBody
    public ArrayList<ArrayList<Recruit>> match(String category, String province, String condition, HttpServletRequest request){
        Map<String, String> map = CookieUtil.getCookies(request);
        String username = map.get("username");
        queue.add(username);
        List<String> ss=recruitService.surplus(category,province,condition);
        ArrayList<ArrayList<Recruit>> arrayList=new ArrayList();
        if(ss!=null){
            for(String str:ss){
                ArrayList<Recruit> list= (ArrayList<Recruit>) recruitService.FindByJob(str);
                arrayList.add(list);
            }
        }
        return arrayList;

    }
}