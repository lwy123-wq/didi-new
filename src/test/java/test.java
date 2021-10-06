import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sun.com.did.Application;
import sun.com.did.dao.JobDao;
import sun.com.did.entity.Login;
import sun.com.did.service.UserServiceImpl;

import java.util.Base64;

@SpringBootTest(classes = Application.class)
@SuppressWarnings("restriction")
public class test {
    @Autowired
    private JobDao jobDao;
    @Autowired
    private UserServiceImpl userService;
    @Test
    public void t(){
    /*    ConfigurableApplicationContext run1 = SpringApplication.run(Application.class);
        String [] definitionNames=run1.getBeanDefinitionNames();

        for (String  name: definitionNames){
            System.out.println(name);
        }*/
//        Login register=new Login("lxj","222","11");
        //userService.insert("wang5","555","888",22);

        /*JobInfo jobInfo=new JobInfo("aa","11"," ","","","","","",2,"");
        jobDao.create("aa","11"," ","","","","","",2,"");*/

        //找回密码demo，可运行
        Login password = userService.findPassword("Oooo", "97@qq.com");
        System.out.println("========================");
        byte[] decoded=Base64.getDecoder().decode(password.getPasswd());
        String decodeStr=new String(decoded);
        System.out.println(decodeStr);

    }

}
