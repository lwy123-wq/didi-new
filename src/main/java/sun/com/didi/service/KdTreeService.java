package sun.com.didi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.com.didi.dao.KdTreeDao;
import sun.com.didi.entity.Node;

import java.util.List;

@Service
public class KdTreeService {
    @Autowired
    private KdTreeDao kdTreeDao;
    public Node buildTree(List<Node> nodeList, int index){
        return kdTreeDao.buildKDTree(nodeList,0);

    }
    public List<Node> searchKd(Node root, Node node, int k){
        return kdTreeDao.searchKNN(root,node,k);
    }

}
