
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sun.com.didi.Application;
import sun.com.didi.dao.JobDao;

import sun.com.didi.service.IntentionImpl;
import sun.com.didi.service.UserServiceImpl;
import sun.com.didi.service.WorkServiceImpl;
import sun.com.didi.service.UnitServiceImpl;

import javax.annotation.Resource;

@SpringBootTest(classes = Application.class)
@SuppressWarnings("restriction")
public class test {
    @Autowired
    private JobDao jobDao;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private IntentionImpl intention;
    @Autowired
    private WorkServiceImpl workService;
    @Resource
    private UnitServiceImpl unitService;
    @Test
    public void t() throws Exception {
//        Intention aa=new Intention("aa","ii","kk","ss","jj","hh","kk");
//        intention.jobwanted("aa","ii","kk","ss","jj","hh","kk");
    /*    ConfigurableApplicationContext run1 = SpringApplication.run(Application.class);
        String [] definitionNames=run1.getBeanDefinitionNames();

        for (String  name: definitionNames){
            System.out.println(name);
        }*/
//        Login register=new Login("lxj","222","11");
        //userService.insert("wang5","555","888",22);

        /*JobInfo jobInfo=new JobInfo("aa","11"," ","","","","","",2,"");
        jobDao.create("aa","11"," ","","","","","",2,"");*/

        //找回密码demo，可运行
    /*    Login password = userService.findPassword("wang", "2079606166@qq.com");
        System.out.println(password.getPasswd());
        System.out.println("========================");
        byte[] decoded= Base64.getDecoder().decode(password.getPasswd());
        String decodeStr=new String(decoded);
        System.out.println(decodeStr);*/

        //lucene搜索引擎
     /*       Directory directory = FSDirectory.open(new File("/home/lxj/文档/index").toPath());
            IndexReader indexReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);
            //创建查询对象
            Query query = new TermQuery(new Term("SalaryRequirements", "10"));
            //执行查询
            TopDocs topDocs = indexSearcher.search(query, 1);
            //共查询到的document个数
            System.out.println("查询结果总数量：" + topDocs.totalHits);
            //遍历查询结果
            for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
                Document document = indexSearcher.doc(scoreDoc.doc);
                System.out.println(document.get("OccupationalCategory"));
                System.out.println(document.get("company"));
                System.out.println(document.get("RemainingPositions"));
                System.out.println(document.get("SalaryRequirements"));
                System.out.println(document.get("Number"));
            }
            //关闭indexreader
            indexSearcher.getIndexReader().close();*/

    }

}
