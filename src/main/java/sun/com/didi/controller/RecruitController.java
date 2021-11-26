package sun.com.didi.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.com.didi.entity.Recruit;
import sun.com.didi.service.RecruitServiceImpl;
import sun.com.didi.util.CookieUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public String Recruit(HttpServletRequest request, HttpServletResponse response,String Rec_company,String Rec_logo, String Rec_job,String Rec_category, String Rec_salary, String Rec_Duration, String Rec_experience) throws IOException {
        Recruit unit =unitService.findByCompany(Rec_company);
        int expire = 60 * 60 * 24 * 7;  //表示7天
        if (unit.getRec_company()==null){
            byte[] imageBinary = getImageBinary(Rec_logo);
            int insert = unitService.insert(Rec_company, imageBinary, Rec_job,Rec_category, Rec_salary, Rec_Duration, Rec_experience);
            CookieUtil.setCookie(request, response, "company",unit.getRec_company(), expire);
            if (insert>0){
                return "数据添加成功！";
            }
        }
        return "该公司或公司logo已注册！";

    }
    public byte[] getImageBinary(String Rec_logo) throws IOException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(Rec_logo));
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);

        byte[] temp = new byte[1024];
        int size = 0;
        while ((size = in.read(temp)) != -1) {
            out.write(temp, 0, size);
        }
        in.close();
        byte[] b = out.toByteArray();//得到二进制流
        return  buildFolder(b);
    }
    public byte[] buildFolder(byte[] path)
    {
        //读取文件夹路径
        File file = new File("src/num");
        //判断是否存在
        if (!file.exists() && !file.isDirectory())
        {
            try
            {
                System.out.println("文件夹不存在！");
                //生成文件夹
                file.mkdir();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("文件夹存在！");
        }

        return path;
    }
    @PostMapping(value = "/selectRecruit")
    @ResponseBody
    public List<Recruit> selectRecruit(){
        return unitService.select();
    }
}
