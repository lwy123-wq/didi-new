package sun.com.didi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.com.didi.util.LruCacheUtil;

import java.net.URLDecoder;
import java.util.ArrayList;

@Controller
public class HostDataController {
    private static int i=1;
    @PostMapping(value = "/LRU")
    @ResponseBody
    public  ArrayList<String> lru(@RequestBody String str) throws Exception {
        String doc = URLDecoder.decode(str, "utf-8");
        String stri[] = doc.split("=");
        String query = stri[1];
        LruCacheUtil cache = new LruCacheUtil(2);
        ArrayList<String> arrayList=new ArrayList<>();

        for ( ;i<=3;i++){
            cache.put(i, query);
            System.out.println(i);
            break;
        }

        for (int j=1;j<=3;j++){
            System.out.println(cache.get(j));
            arrayList.add((String) cache.get(j));
        }
        return arrayList;
    }
}
