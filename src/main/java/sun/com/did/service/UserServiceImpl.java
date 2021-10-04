package sun.com.did.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.com.did.dao.LoginDao;
import sun.com.did.entity.Login;
import sun.com.did.entity.JobLogin;

@Service
public class UserServiceImpl {

    @Autowired
    private LoginDao loginDao;


    public Login findByName(String username) {
        return loginDao.findByName(username);
    }

    public int insert(String name, String passwd,String email){

        return loginDao.insertUser(new Login(name,passwd,email));
    }
    public Login findByNameAndPassword(String username, String password){
        return loginDao.findByNameAndPassword(username,password);
    }
    public Login findPassword(String name,String email){
        return  loginDao.findPassword(name,email);
    }
}
