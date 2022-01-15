package sun.com.didi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    @RequestMapping(value = "/map", method = RequestMethod.GET)
    public String map(){
        return "map";
    }
    @Autowired
    private KdTreeService kdTreeService;
    @Autowired
    private CoordinateService coordinateService;

    static List<Node> nodeList=new ArrayList<>();
    static List<String> nodeList1=new ArrayList<>();
    static List<Coordinate> listArrayList=new ArrayList<>();
    static List<Coordinate> list;
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

        System.out.println("success insert kdTree");
        return "success";
    }
    @PostMapping(value ="/searchNode" )
    @ResponseBody
    public List<Coordinate> searchNode(@RequestBody String sum) throws UnsupportedEncodingException {
        String doc = URLDecoder.decode(sum, "utf-8");
        String str[] = doc.split("&");
        String str1[]=str[0].split("=");
        String str2[]=str[1].split("=");
        String str3[]=str[2].split("=");
        double sum1 = Double.valueOf(str1[1].toString());
        double sum2 = Double.valueOf(str2[1].toString());
        int sum3=Integer.valueOf(str3[1].toString());
        list= coordinateService.select(); //查找数据库所有经度纬度

        for (Coordinate coor:list){
            nodeList.add(new Node(new double[]{coor.getLongitude(),coor.getLatitude()})) ;
        }
        Node root=kdTreeService.buildTree(nodeList,0);
        List<Node> list1=kdTreeService.searchKd(root,new Node(new double[]{sum1,sum2}),sum3);
        for (Node node:list1){
            Coordinate coo=coordinateService.selectCoordinate(node.getData(0),node.getData(1));
            nodeList1.add(coo.getCompanyName());
        }
        for (int i=0;i<list1.size();i++){
            System.out.println(list1.get(i).getData(0)+"uu"+list1.get(i).getData(1)+"oo"+nodeList1.get(i));
            Coordinate coordinate=new Coordinate(list1.get(i).getData(0),list1.get(i).getData(1),nodeList1.get(i));
            listArrayList.add(coordinate);
        }
        return listArrayList;

    }


}
