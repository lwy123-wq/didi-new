package sun.com.didi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.com.didi.dao.PictureDao;
import sun.com.didi.entity.Picture;

@Service
public class PictureServiceImpl {
    @Autowired
    private PictureDao pictureDao;
    public int insertPicture(Picture picture){
        return pictureDao.insert(picture.getTime(),picture.getFileName(),picture.getCompanyName());

    }
}
