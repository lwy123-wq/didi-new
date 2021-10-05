package sun.com.did.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import sun.com.did.entity.Login;
import sun.com.did.service.UserServiceImpl;

import java.nio.charset.StandardCharsets;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    //登录
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        return "index";
    }
    @RequestMapping(value = "/login")
    public String login(){
        return "/login";
    }
    @PostMapping(value = "/loginn")
    @ResponseBody
    /*password=DigestUtils.md5DigestAsHex(password.getBytes());*/
    public String login(String username,String password){
        System.out.println(username);
        Login user=userService.findByNameAndPassword(username, DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)));

        if(user.getName()==null||user.getPasswd()==null){
            return "error";
        }else {
            return "success";
        }
    }
    //注册
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String start(){
        return "register";
    }
    @PostMapping(value = "/registry")
    @ResponseBody
    public String  register(String username,String password,String email){
        Login user =userService.findByName(username);
        if(user.getName() == null){
            //personService.register(id);
            userService.insert(username,DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)),email);
            return "Y";
        }
        return "N";
    }
    //找回密码
    @RequestMapping(value = "/FindPasswd",method = RequestMethod.GET)
    public String FindPasswd(){
        return "/FindPasswd";
    }
    @PostMapping(value = "/find")
    @ResponseBody
    public String FindPasswd( String username,String email){
        Login user=userService.findPassword(username,email);
        System.out.println(user.getPasswd());
        if (user.getPasswd()!=null){
            return "找回成功！"+user.getPasswd();
        }
        return "该用户不存在！";
    }
    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public String select(){
        return "select";
    }


    @RequestMapping(value = "/qiuzhi", method = RequestMethod.GET)
    public String qiuzhi(){
        return "qiuzhi";
    }

    @RequestMapping(value = "/employment", method = RequestMethod.GET)
    public String employment(){
        return "employment";
    }
}
