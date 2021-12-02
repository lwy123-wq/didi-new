package sun.com.didi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sun.com.didi.service.TrieTreeImpl;

import java.util.ArrayList;

@RestController
public class TrieTreeController {
  @Autowired
    private TrieTreeImpl trieTre;

    @PostMapping(value = "/Select")
    @ResponseBody
  public ArrayList<String> query(@RequestBody String str){
        ArrayList<String> list=trieTre.search(str);
        return list;
  }
}
