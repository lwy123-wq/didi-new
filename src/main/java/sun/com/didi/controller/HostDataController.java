package sun.com.didi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.com.didi.util.LruCacheUtil;

@Controller
public class HostDataController {
    @PostMapping(value = "/LRU")
    @ResponseBody
    public LruCacheUtil lru(String str){
        LruCacheUtil cache = new LruCacheUtil(2);
        for (int i=1;i<=3;i++) {
            cache.put(i, str);
        }
        for (int j=1;j<=3;j++){
            System.out.println(cache.get(j));
        }
        return cache;
    }
}
