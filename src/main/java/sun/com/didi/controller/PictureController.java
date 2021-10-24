package sun.com.didi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import sun.com.didi.entity.Picture;
import sun.com.didi.service.PictureServiceImpl;

import javax.mail.Multipart;

@Controller
public class PictureController {
    @Autowired
    private PictureServiceImpl pictureService;
/*
    public String insertPicture(MultipartFile file){
        Picture picture=new Picture();

    }*/
}
