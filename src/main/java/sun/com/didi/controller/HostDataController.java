package sun.com.didi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.com.didi.service.LruService;
import sun.com.didi.util.LruCacheUtil;

import java.net.URLDecoder;
import java.util.ArrayList;

@Controller
public class HostDataController {
    @Autowired
    private LruService lruService;
    private static int sum=0;
    @PostMapping(value = "/LRU")
    @ResponseBody
    public   ArrayList<String> lru(@RequestBody String str) throws Exception {
        ArrayList<String> arrayList=new ArrayList<>();
        String doc = URLDecoder.decode(str, "utf-8");
        String stri[] = doc.split("=");
        String query = stri[1];
        LruCacheUtil methods = lruService.methods(query);
        for (int i=0;i<=2;i++){
            arrayList.add((String) methods.get(sum));
            sum++;
        }
        return arrayList;
    }
}
