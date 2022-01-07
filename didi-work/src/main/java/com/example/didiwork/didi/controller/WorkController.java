package com.example.didiwork.didi.controller;

import com.example.didiwork.didi.entity.Requirement;
import com.example.didiwork.didi.service.WorkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WorkController {

    @Autowired
    private WorkServiceImpl workService;

    @RequestMapping(value = "/recruit", method = RequestMethod.GET)
    public String start(){
        return "recruit";
    }
    @PostMapping(value = "")
    @ResponseBody
    public String  Recruitment(int uid){
        Requirement user =workService.selectWork(uid);
        if(user == null){
            return "Y";
        }
        return "N";
    }
}
