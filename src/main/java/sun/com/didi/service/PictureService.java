package sun.com.didi.service;

import sun.com.didi.entity.Picture;

import java.util.Date;

public interface PictureService {
    /**
     * 增加图片
     *
     *
     * @return*/
    int insert(Date time, String fileName,String companyName);
    /**
     * 更改图片
     * */
    Picture update(Date time, String fileName);
}
