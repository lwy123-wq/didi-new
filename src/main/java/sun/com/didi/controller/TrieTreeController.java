package sun.com.didi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sun.com.didi.service.TrieTreeImpl;

import java.net.URLDecoder;
import java.util.List;

@RestController
public class TrieTreeController {
  @Autowired
    private TrieTreeImpl trieTre;

    @PostMapping(value = "/Select")
    @ResponseBody
  public  List<String> Select(@RequestBody String str) throws Exception {
        String s = URLDecoder.decode(str,"UTF-8");
        String stri[] = s.split("=");
        String query = stri[1];
        System.out.println(query);
        List<String> list=trieTre.search(query);
        System.out.println(list);
        return list;
  }
}
