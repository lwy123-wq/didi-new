package sun.com.did.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.com.did.code.UtilCode;
import sun.com.did.entity.Intention;
import sun.com.did.entity.Login;
import sun.com.did.service.IEmailService;
import sun.com.did.service.IntentionImpl;
import sun.com.did.service.UserServiceImpl;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Controller
public class UserController {
    public static String s;
    @Autowired
    private UserServiceImpl userService;
    @Resource
    private IEmailService emailService;
    @Resource
    private IntentionImpl intention;
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
        System.out.println(username+"dddddddddddddddd");
        Login user=userService.findByNameAndPassword(username, Base64.getEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_8)));
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
            userService.insert(username, Base64.getEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_8)),email);
            return "Y";
        }
        return "N";
    }
    //找回密码
    /*@RequestMapping(value = "/ReturnPasswd",method = RequestMethod.GET)
    public String FindPasswd(){
        return "forget";
    }*/
    @PostMapping(value = "/find")
    @ResponseBody
    public String forget( String username,String email){
        Login user=userService.findPassword(username,email);
        System.out.println("================");
        System.out.println(user.getPasswd());
        byte[] decoded=Base64.getDecoder().decode(user.getPasswd());
        String decodeStr=new String(decoded);
       // System.out.println(decodeStr);//输出解密之后的密码
        if (user.getPasswd()!=null){
            return decodeStr;
        }
        return "error";
    }

    //验证码校验
    @RequestMapping(value = "/forget",method = RequestMethod.GET)
    public String check(){
        return "forget";
    }
    @PostMapping(value = "/matching")
    @ResponseBody
    public String check(String code){
        if (s.equals(code)){
            return "success";
        }else {
            return "error";
        }

    }


    //发送验证码
    @PostMapping(value = "/SendCode")
    @ResponseBody
    public  String jump(String email){
        UtilCode utilCode=new UtilCode();
        s = utilCode.verifyCode();
        boolean b = sendEmail(email, s);
        if (b==true){
            return "success";
        }
        return "error";
    }

    public boolean sendEmail(String to,String contentText){
        return emailService.sendAttachmentMail(to,contentText);

    }
    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public String select(){
        return "select";
    }


    //求职页未写完
    @RequestMapping(value = "/qiuzhi", method = RequestMethod.GET)
    public String qiuzhi(){
        return "qiuzhi";
    }
    @PostMapping(value = "/jobwanted")
    @ResponseBody
    public String jobwanted(String port,String Category,String province,String city,String condition,String Duration,String experience){
        int u=intention.jobwanted(port, Category, province, city,condition, Duration,experience);
        if (u!=0){
            return "success";
        }

        return "error";

    }

    @RequestMapping(value = "/employment", method = RequestMethod.GET)
    public String employment(){
        return "employment";
    }
}
