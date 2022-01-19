package sun.com.didi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import sun.com.didi.dao.LoginDao;
import sun.com.didi.entity.Login;

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

    public Login findEmail(String name){
        return loginDao.findEmail(name);

    }
    public int insertToken(String token,String name){
        return loginDao.insertToken(token,name);
    }
    public Login selectToken(String name){
        return loginDao.selectToken(name);
    }
}
