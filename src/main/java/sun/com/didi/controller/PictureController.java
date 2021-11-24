package sun.com.didi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.com.didi.entity.Picture;
import sun.com.didi.service.PictureServiceImpl;

import javax.mail.Multipart;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@Controller
public class PictureController {
    @Autowired
    private PictureServiceImpl pictureService;
    @PostMapping(value = "/picture")
    @ResponseBody
    public String insertPicture(MultipartFile file,String companyName) throws IOException {
        Picture picture=new Picture();
        String filePath = "/home/lxj/图片/图片";
        String originalFilename = file.getOriginalFilename();
        //新的文件名字
        String newFileName = new Date().getTime() + originalFilename;
        //封装上传文件位置的全路径
        File targetFile = new File(filePath,newFileName);
        //把本地文件上传到封装上传文件位置的全路径
        file.transferTo(targetFile);
        Date date=new Date();
        Date sqlDate=new Date(date.getTime());
        picture.setTime(sqlDate);
        picture.setFileName(newFileName);
        picture.setCompanyName(companyName);
        if(file!=null){
            pictureService.insertPicture(picture);
            return "success";
        }else {
            return "error";
        }
    }
}
