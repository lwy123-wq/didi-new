package sun.com.didi.controller;

import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.com.didi.util.CookieUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

@Controller
public class MatchController {
    static ConcurrentLinkedQueue queue=new ConcurrentLinkedQueue();

    @PostMapping(value = "/match")
    @ResponseBody
    public String match(String category, String province, String condition, HttpServletRequest request){
        Map<String, String> map = CookieUtil.getCookies(request);
        String username = map.get("username");
        queue.add(username);


    }
}
