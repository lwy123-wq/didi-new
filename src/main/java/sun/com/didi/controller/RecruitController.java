package sun.com.didi.controller;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.com.didi.entity.Recruit;
import sun.com.didi.service.RecruitServiceImpl;
import sun.com.didi.util.CookieUtil;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;


@Controller
public class RecruitController {
    @Resource
    private RecruitServiceImpl unitService;

    @RequestMapping(value = "/employment", method = RequestMethod.GET)
    public String employment(){
        return "employment";
    }

    @PostMapping(value = "/Recruit")
    @ResponseBody
    @Cacheable(cacheNames = "Recruit",key = "#Rec_company+'-'+#Rec_logo+'-'+#Rec_category+'-'+#Rec_salary+'-'+#Rec_Duration+'-'+#Rec_experience")
    public String Recruit(HttpServletRequest request, HttpServletResponse response, String Rec_company, String Rec_logo, String Rec_job, String Rec_category, String Rec_salary, String Rec_Duration, String Rec_experience) throws Exception {
        Recruit unit =unitService.findByCompany(Rec_company);
        int expire = 60 * 60 * 24 * 7;  //表示7天
        if (unit.getRec_company()==null){
          //  String imageBinary = getImageBinary(Rec_logo);
            int insert = unitService.insert(Rec_company, Rec_logo, Rec_job,Rec_category, Rec_salary, Rec_Duration, Rec_experience);
            CookieUtil.setCookie(request, response, "company",unit.getRec_company(), expire);
            if (insert>0){
                return "数据添加成功！";
            }
        }
        return "该公司或公司logo已注册！";

    }
   /* public String getImageBinary(String Rec_logo) throws Exception {
        String pic = GetContent(Rec_logo);
        return pic;
    }
    public String GetContent(String filepath) throws Exception//将指定路径下的文件转换成二进制代码，用于传输到数据库
    {
        System.out.println(filepath);
        File f = new File(filepath);
        BufferedImage bi;

            bi = ImageIO.read(f);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "jpeg", baos);
            byte[] bytes = baos.toByteArray();
            return Base64.encodeBase64String(bytes);
           // return encoder.encodeBuffer(bytes).trim();
    }*/
    @PostMapping(value = "/selectRecruit")
    @ResponseBody
    public List<Recruit> selectRecruit(){
        return unitService.select();
    }
}
