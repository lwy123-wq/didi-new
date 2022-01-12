package sun.com.didi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.com.didi.entity.Node;
import sun.com.didi.service.KdTreeService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class KdTreeController {
    @Autowired
    private KdTreeService kdTreeService;
    static List<Node> nodeList=new ArrayList<>();
    @PostMapping(value = "/insertNode")
    @ResponseBody
    public void insertNode(double a,double b){
      nodeList.add(new Node(new double[]{a,b})) ;
      kdTreeService.buildTree(nodeList,0);
      System.out.println("success insert kdTree");
    }

}
