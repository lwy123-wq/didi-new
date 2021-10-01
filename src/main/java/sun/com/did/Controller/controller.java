package sun.com.did.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.com.did.entity.Login;
import sun.com.did.service.UserServiceImpl;

@Controller
public class controller {

    @Autowired
    private UserServiceImpl userService;

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
    public String login(String username,String password){
        System.out.println(username);
        Login user=userService.findByNameAndPassword(username,password);

        if(user.getName()==null||user.getPasswd()==null){
            return "error";
        }else {
            return "success";
        }
    }
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String start(){
        return "register";
    }
    @PostMapping(value = "/registry")
    @ResponseBody
    public String  register(String username,String password,String email,int code){
        System.out.println("duan dian 1");
        Login user =userService.findByName(username);
        System.out.println(user.getName());
        if(user.getName() == null){
            //personService.register(id);
            userService.insert(username,password,email,code);
            return "Y";
        }
        return "N";
    }
    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public String select(){
        return "select";
    }

}
