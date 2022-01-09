package sun.com.didi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.com.didi.entity.Detailed;
import sun.com.didi.service.DetailedServiceImpl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

@Controller
public class DetailedController {

    @Autowired
    DetailedServiceImpl detailedService;
    @RequestMapping(value = "/detailed",method = RequestMethod.GET)
    public String detail(){
        return "details";
    }
    @PostMapping(value = "/selectDetail")
    @ResponseBody
    //查询公司的详细信息和名称
    public ArrayList<Detailed> selectDetail(@RequestBody String company) throws Exception {
        String str = URLDecoder.decode(company, "utf-8");
        String stri[] = str.split("=");
        String query = stri[1];
        return  detailedService.SelectDetailed(query);
    }

    @PostMapping(value = "/InsertDetailed")
    @ResponseBody
    //插入公司的详细信息和名称
    public String InsertDetailed(@RequestBody String company) throws Exception {
        String str = URLDecoder.decode(company, "utf-8");
        String stri[] = str.split("&Rec");
        String per=stri[0];
        String dem=stri[1];
        String str1[] = per.split("=");
        String str2[] = dem.split("=");
        int insert = detailedService.insertDetailed(str1[1], str2[1]);
        if (insert>0){
            return "success";
        }
        else return "error";
    }

}
