package sun.com.didi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.com.didi.entity.Recruit;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

class TrieNode {
    public String value;
    public ArrayList<TrieNode> ptr = null;
    public TrieNode(String value) {
        this.value=value;
        ptr =new ArrayList<TrieNode>();
    }
}
@Service
public class TrieTreeImpl {
    @Autowired
    private RecruitServiceImpl recruitService;
    public String value;
    public ArrayList<TrieNode> ptr = null;
    public TrieTreeImpl(String value) {
        this.value=value;
        ptr =new ArrayList<TrieNode>();
    }

    private static TrieNode root = null;
    ArrayList<String> searchResult = new ArrayList<String>();
    StringBuffer tempWord = new StringBuffer();
    int start = 0;

    public TrieTreeImpl() {
        root = new TrieNode(null);
    }
    @PostConstruct()
    public   ArrayList<Recruit> CreateAdd(){
        ArrayList<Recruit> company =recruitService.findCompany();
        ArrayList<Recruit> byCategory = recruitService.findByCategory();
        TrieTreeImpl t=new TrieTreeImpl();
        for (int i=0;i<=company.size()-1;i++){
            t.insert(company.get(i).getRec_company());
            System.out.println(company.get(i).getRec_company());
            t.insert(byCategory.get(i).getRec_category());
            System.out.println(byCategory.get(i).getRec_category());

        }

        return company;
    }
    public void insert(String key) {
        TrieNode p = root;
        String tempWord;
        boolean contains;
        TrieNode tempNode;
        for (int i = 0; i < key.length(); i++) {
            tempWord = String.valueOf(key.charAt(i));
            contains = false;
            for (TrieNode tn : p.ptr) {
                if (tn.value.equals(tempWord)) {
                    p = tn;
                    contains = true;
                    break;
                }
            }
            if (!contains) {
                tempNode = new TrieNode(tempWord);
                p.ptr.add(tempNode);
                p = tempNode;
            }
        }
    }

    public ArrayList<String> search(String key) {  //模糊查询就是这个方法，打个比方比如key是"ap"，那么ArrayList里就有{"apple","application"}
        TrieNode p = root;
        String temp;
        boolean contains = false;
        for (int i = 0; i < key.length(); i++) {
            temp = String.valueOf(key.charAt(i));
            contains = false;
            for (TrieNode tn : p.ptr) {
                if (tn.value.equals(temp)) {
                    p = tn;
                    contains = true;
                    break;
                }
            }
            if (contains) {
                continue;
            } else {
                break;
            }
        }
        if (contains) {
            if (!(p.ptr.isEmpty())) {
                //查找到关键字
                searchResult.clear();
                tempWord.delete(0, tempWord.length());
                tempWord.append(key);
                start = key.length();
                traverseTree(p);
            } else {
                //已经查找到键树的底部
                return null;
            }
        } else {
            //没有查找到相应关键字
            return null;
        }
        return searchResult;
    }

    private void traverseTree(TrieNode p) {
        if (!(p.ptr.isEmpty())) {
            for (TrieNode tn : p.ptr) {
                tempWord.append(tn.value);
                start++;
                traverseTree(tn);
                start--;
                tempWord.delete(start, tempWord.length());
            }
        } else {
            searchResult.add(tempWord.toString());
        }
    }
}
