package sun.com.didi.dao;

import org.springframework.stereotype.Service;
import sun.com.didi.entity.Node;

import java.util.ArrayList;
import java.util.List;

@Service
public class KdTreeDao {
    /**
     * 构建kd树  返回根节点
     * @param nodeList
     * @param index
     * @return
     */
    public Node buildKDTree(List<Node> nodeList, int index)
    {
        if(nodeList==null || nodeList.size()==0)
            return null;
        quickSortForMedian(nodeList,index,0,nodeList.size()-1);//中位数排序
        Node root=nodeList.get(nodeList.size()/2);//中位数 当做根节点
        root.dim=index;
        List<Node> leftNodeList=new ArrayList<Node>();//放入左侧区域的节点  包括包含与中位数等值的节点-_-
        List<Node> rightNodeList=new ArrayList<Node>();

        for(Node node:nodeList)
        {
            if(root!=node)
            {
                if(node.getData(index)<=root.getData(index))
                    leftNodeList.add(node);//左子区域 包含与中位数等值的节点
                else
                    rightNodeList.add(node);
            }
        }

        //计算从哪一维度切分
        int newIndex=index+1;//进入下一个维度
        if(newIndex>=root.data.length)
            newIndex=0;//从0维度开始再算


        root.left=buildKDTree(leftNodeList,newIndex);//添加左右子区域
        root.right=buildKDTree(rightNodeList,newIndex);

        if(root.left!=null)
            root.left.parent=root;//添加父指针
        if(root.right!=null)
            root.right.parent=root;//添加父指针
        return root;
    }


    /**
     * 查询最近邻
     * @param root kd树
     * @param q 查询点
     * @param k
     * @return
     */
    public List<Node> searchKNN(Node root,Node q,int k)
    {
        List<Node> knnList=new ArrayList<Node>();
        searchBrother(knnList,root,q,k);
        return knnList;
    }

    /**
     * searhchBrother
     * @param knnList
     * @param k
     * @param q
     */
    public void searchBrother(List<Node> knnList, Node root, Node q, int k) {
//         Node almostNNode=root;//近似最近点
        Node leafNNode=searchLeaf(root,q);
        double curD=q.computeDistance(leafNNode);//最近近似点与查询点的距离 也就是球体的半径
        leafNNode.distance=curD;
        maintainMaxHeap(knnList,leafNNode,k);
        //System.out.println("leaf1"+leafNNode.getData(leafNNode.parent.dim));
        while(leafNNode!=root)
        {
            if (getBrother(leafNNode)!=null) {
                Node brother=getBrother(leafNNode);
                //System.out.println("brother1"+brother.getData(brother.parent.dim));
                if(curD>Math.abs(q.getData(leafNNode.parent.dim)-leafNNode.parent.getData(leafNNode.parent.dim))||knnList.size()<k)
                {
                    //这样可能在另一个子区域中存在更加近似的点
                    searchBrother(knnList,brother, q, k);
                }
            }
            //System.out.println("leaf2"+leafNNode.getData(leafNNode.parent.dim));
            leafNNode=leafNNode.parent;//返回上一级
            double rootD=q.computeDistance(leafNNode);//最近近似点与查询点的距离 也就是球体的半径
            leafNNode.distance=rootD;
            maintainMaxHeap(knnList,leafNNode,k);
        }
    }


    /**
     * 获取兄弟节点
     * @param node
     * @return
     */
    public Node getBrother(Node node)
    {
        if(node==node.parent.left)
            return node.parent.right;
        else
            return node.parent.left;
    }

    /**
     * 查询到叶子节点
     * @param root
     * @param q
     * @return
     */
    public Node searchLeaf(Node root,Node q)
    {
        Node leaf=root,next=null;
        int index=0;
        while(leaf.left!=null || leaf.right!=null)
        {
            if(q.getData(index)<leaf.getData(index))
            {
                next=leaf.left;//进入左侧
            }else if(q.getData(index)>leaf.getData(index))
            {
                next=leaf.right;
            }else{
                //当取到中位数时  判断左右子区域哪个更加近
                if(q.computeDistance(leaf.left)<q.computeDistance(leaf.right))
                    next=leaf.left;
                else
                    next=leaf.right;
            }
            if(next==null)
                break;//下一个节点是空时  结束了
            else{
                leaf=next;
                if(++index>=root.data.length)
                    index=0;
            }
        }

        return leaf;
    }

    /**
     * 维护一个k的最大堆
     * @param listNode
     * @param newNode
     * @param k
     */
    public void maintainMaxHeap(List<Node> listNode,Node newNode,int k)
    {
        if(listNode.size()<k)
        {
            maxHeapFixUp(listNode,newNode);//不足k个堆   直接向上修复
        }else if(newNode.distance<listNode.get(0).distance){
            //比堆顶的要小   还需要向下修复 覆盖堆顶
            maxHeapFixDown(listNode,newNode);
        }
    }

    /**
     * 从上往下修复  将会覆盖第一个节点
     * @param listNode
     * @param newNode
     */
    private void maxHeapFixDown(List<Node> listNode,Node newNode)
    {
        listNode.set(0, newNode);
        int i=0;
        int j=i*2+1;
        while(j<listNode.size())
        {
            if(j+1<listNode.size() && listNode.get(j).distance<listNode.get(j+1).distance)
                j++;//选出子结点中较大的点，第一个条件是要满足右子树不为空

            if(listNode.get(i).distance>=listNode.get(j).distance)
                break;

            Node t=listNode.get(i);
            listNode.set(i, listNode.get(j));
            listNode.set(j, t);

            i=j;
            j=i*2+1;
        }
    }

    private void maxHeapFixUp(List<Node> listNode,Node newNode)
    {
        listNode.add(newNode);
        int j=listNode.size()-1;
        int i=(j+1)/2-1;//i是j的parent节点
        while(i>=0)
        {

            if(listNode.get(i).distance>=listNode.get(j).distance)
                break;

            Node t=listNode.get(i);
            listNode.set(i, listNode.get(j));
            listNode.set(j, t);

            j=i;
            i=(j+1)/2-1;
        }
    }



    /**
     * 使用快排进进行一个中位数的查找  完了之后返回的数组size/2即中位数
     * @param nodeList
     * @param index
     * @param left
     * @param right
     */
    //@Test
    private void quickSortForMedian(List<Node> nodeList,int index,int left,int right)
    {
        if(left>=right || nodeList.size()<=0)
            return ;

        Node kn=nodeList.get(left);
        double k=kn.getData(index);//取得向量指定索引的值

        int i=left,j=right;

        //控制每一次遍历的结束条件，i与j相遇
        while(i<j)
        {
            //从右向左找一个小于i处值的值，并填入i的位置
            while(nodeList.get(j).getData(index)>=k && i<j)
                j--;
            nodeList.set(i, nodeList.get(j));
            //从左向右找一个大于i处值的值，并填入j的位置
            while(nodeList.get(i).getData(index)<=k && i<j)
                i++;
            nodeList.set(j, nodeList.get(i));
        }

        nodeList.set(i, kn);


        if(i==nodeList.size()/2)
            return ;//完成中位数的排序了，但并不是完成了所有数的排序，这个终止条件只是保证中位数是正确的。去掉该条件，可以保证在递归的作用下，将所有的树
            //将所有的数进行排序

        else if(i<nodeList.size()/2)
        {
            quickSortForMedian(nodeList,index,i+1,right);//只需要排序右边就可以了
        }else{
            quickSortForMedian(nodeList,index,left,i-1);//只需要排序左边就可以了
        }
    }
}
