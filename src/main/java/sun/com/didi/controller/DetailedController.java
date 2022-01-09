package sun.com.didi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.com.didi.entity.Detailed;
import sun.com.didi.service.DetailedServiceImpl;

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
    public ArrayList<Detailed> selectDetail(String company){
        return  detailedService.SelectDetailed(company);
    }

    @PostMapping(value = "/InsertDetailed")
    @ResponseBody
    //插入公司的详细信息和名称
    public String InsertDetailed(String company,String Information){
        int insert = detailedService.insertDetailed(company, Information);
        if (insert>0){
            return "数据添加成功！";
        }
        else return "数据添加失败！";
    }

}
