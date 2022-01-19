package sun.com.didi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.com.didi.entity.Recruit;
import sun.com.didi.entity.Report;
import sun.com.didi.service.RunTimeServiceImpl;
import sun.com.didi.util.CookieUtil;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.Map;

@Controller
public class RunTimeController {
    public static boolean chech =false;
    int T;
    //签到
    @Autowired
    private RunTimeServiceImpl runTimeService;
    @PostMapping(value = "/OF")
    @ResponseBody
    public boolean tupleof(HttpServletRequest request, @RequestBody String check_times) throws Exception {
        Map<String, String> map = CookieUtil.getCookies(request);
        String username = map.get("username");
        String s = URLDecoder.decode(check_times, "UTF-8");
        String stri[] = s.split("=");
        String query = stri[1];

        String stri2[] = query.split("=");
        String query2 = stri2[0];

        Report runtime = runTimeService.runtime(username);
        String utcTime = runtime.getTime();
        if (utcTime.substring(1).equals("年")==true||utcTime.substring(1).equals("月")==true) {
            String str[] = utcTime.split("年");
            String demo = utcTime.substring(1);
            String Tim = str[0];
            if (demo.equals("年")) {
                int time = Integer.parseInt(Tim);
                T = time * 365;
            } else {
                int time = Integer.parseInt(Tim);
                T = time * 31;
            }
            if (T < 0) {
                return true;
                /*结算工资*/
            } else {
                if (RunTimeController.chech == false) {
                    int timeplus = T - 1;
                    String s1 = Integer.toString(timeplus);
                    if (runTimeService.update(s1, username) == 1) {
                        chech = chech(Integer.parseInt(query2));
                        return chech;
                    }
                }
            }
        }else {
            int time = Integer.parseInt(utcTime);
            if (RunTimeController.chech == false) {
                int timeplus = time - 1;
                String s1 = Integer.toString(timeplus);
                if (runTimeService.update(s1, username) == 1) {
                    chech = chech(Integer.parseInt(query2));
                    return chech;
                }
            }
        }
       return chech;
    }
    @PostMapping(value = "/false")
    @ResponseBody
   // @Scheduled(cron = "0 0 23 * * ?")
    public boolean ReturnCheck(){
        return RunTimeController.chech;
    }
    public boolean chech(int check){
        if (check>=1){
            return true;
        }
        return false;
    }
    /*续约*/
    @PostMapping(value = "/offtime")
    @ResponseBody
    public String Renewal(@RequestBody String time) throws Exception {
        String s = URLDecoder.decode(time, "UTF-8");
        String stri[] = s.split("=");
        String query = stri[1];
        int insert = runTimeService.Insert(query);
        if (insert>0){
            return "success";
        }else
            return "error";
    }
}
