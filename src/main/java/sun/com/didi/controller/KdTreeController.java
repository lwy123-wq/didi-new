package sun.com.didi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.com.didi.entity.Coordinate;
import sun.com.didi.entity.Node;
import sun.com.didi.service.CoordinateService;
import sun.com.didi.service.KdTreeService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@Controller
public class KdTreeController {
    @Autowired
    private KdTreeService kdTreeService;
    @Autowired
    private CoordinateService coordinateService;

    static List<Node> nodeList=new ArrayList<>();
    @PostMapping(value = "/insertNode")
    @ResponseBody
    public String insertNode(@RequestBody String sum) throws UnsupportedEncodingException {
        String doc = URLDecoder.decode(sum, "utf-8");
        String str[] = doc.split("&");
        String str1[]=str[0].split("=");
        String str2[]=str[1].split("=");
        String str3[]=str[2].split("=");
        double sum1 = Double.valueOf(str1[1].toString());
        double sum2 = Double.valueOf(str2[1].toString());
        coordinateService.insertCoordinate(sum1,sum2,str3[1]);
        List<Coordinate> list= coordinateService.select();

        for (Coordinate coor:list){
            nodeList.add(new Node(new double[]{coor.getLongitude(),coor.getLatitude()})) ;
            System.out.println(coor.getLongitude()+"sssssssssssssssssssssssssssss");
        }
        System.out.println(list.get(0).getLatitude()+"aaaaaaaaaaaaaa");
        kdTreeService.buildTree(nodeList,0);
        System.out.println("success insert kdTree");
        return "success";
    }


}
