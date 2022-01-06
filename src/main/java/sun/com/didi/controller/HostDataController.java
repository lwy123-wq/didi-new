package sun.com.didi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.com.didi.util.LruCacheUtil;

@Controller
public class HostDataController {
    @PostMapping(value = "/LRU")
    @ResponseBody
    public void lru(Object str){
        LruCacheUtil cache = new LruCacheUtil(2);
        int i=1;
        cache.put(i,str);
        System.out.println(cache.get(i));
    }
}
