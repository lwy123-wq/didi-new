package sun.com.didi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.com.didi.util.CodeUtil;
import sun.com.didi.entity.Login;
import sun.com.didi.service.IEmailService;
import sun.com.didi.service.IntentionImpl;
import sun.com.didi.service.UserServiceImpl;
import sun.com.didi.util.CookieUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

@Controller
public class UserController {
    public static String s;
    @Autowired
    private UserServiceImpl userService;
    @Resource
    private IEmailService emailService;

    //登录
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        return "index";
    }
    @RequestMapping(value = "/personal", method = RequestMethod.GET)
    public String personal(){
        return "personal";
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "/login";
    }
    @PostMapping(value = "/loginn")
    @ResponseBody
    /*password=DigestUtils.md5DigestAsHex(password.getBytes());*/
   // @Cacheable(cacheNames = "login",key = "#username+'-'+#password")
    public String login(HttpServletRequest request, HttpServletResponse response, String username, String password, Model model){
        Login user=userService.findByNameAndPassword(username, Base64.getEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_8)));
        //System.out.println(user.getName()+user.getPasswd());
        int expire = 60 * 60 * 24 * 20;  //表示7天
        if(user.getName()==null||user.getPasswd()==null){
            return "error";
        }else {
            CookieUtil.setCookie(request, response, "username", user.getName(), expire);
            CookieUtil.setCookie(request, response, "password", user.getPasswd(), expire);
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
    //@Cacheable(cacheNames = "register",key = "#username+'-'+#password+'-'+#email")
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
    @RequestMapping(value = "/ReturnPasswd",method = RequestMethod.GET)
    public String FindPasswd(){
        return "forget";
    }
    @PostMapping(value = "/find")
    @ResponseBody
    @Cacheable(cacheNames = "ReturnPasswd",key = "#username+'-'+#email")
    public String forget( String username,String email){
        Login user=userService.findPassword(username,email);
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
        CodeUtil utilCode=new CodeUtil();
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


    @RequestMapping(value = "/homePage", method = RequestMethod.GET)
    public String homePage(HttpServletRequest request,Model model){
        Map<String, String> map = CookieUtil.getCookies(request);
        String username = map.get("username");
        String company=map.get("company");
        model.addAttribute("username",username);
        model.addAttribute("company",company);
        return "shou";
    }
    @PostMapping(value = "/token")
    @ResponseBody
    public String token(@RequestBody String token,HttpServletRequest request) throws UnsupportedEncodingException {
        //System.out.println(token+"dddddddddddddd");
        String s = URLDecoder.decode(token,"UTF-8");
        String stri[] = s.split("=");
        String core = stri[1];
        Map<String, String> map = CookieUtil.getCookies(request);
        String username = map.get("username");
        int token1=userService.insertToken(core,username);
        if(token1!=0){
            return "success";
        }
        return "error";
    }
}
