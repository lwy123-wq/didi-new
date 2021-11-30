import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sun.com.didi.Application;
import sun.com.didi.dao.JobDao;
import sun.com.didi.service.IntentionImpl;
import sun.com.didi.service.RecruitServiceImpl;
import sun.com.didi.service.UserServiceImpl;

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
    @Resource
    private RecruitServiceImpl unitService;
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

/*        //lucene搜索引擎
            Directory directory = FSDirectory.open(new File("src/index").toPath());
            IndexReader indexReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);
            //创建查询对象
            Query query = new TermQuery(new Term("Rec_company", "京东"));
            //执行查询
            TopDocs topDocs = indexSearcher.search(query, 3);

           ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            //共查询到的document个数
            System.out.println("查询结果总数量：" + topDocs.totalHits);
            //遍历查询结果
            for (ScoreDoc scoreDoc : scoreDocs) {
                System.out.println("文档id:" + scoreDoc.doc);
                Document document = indexSearcher.doc(scoreDoc.doc);
                System.out.println(document.get("Rec_company"));
                System.out.println(document.get("Rec_logo"));
                System.out.println(document.get("Rec_category"));
                System.out.println(document.get("Rec_salary"));
                System.out.println(document.get("Rec_Duration"));
                System.out.println(document.get("Rec_experience"));
            }
            //关闭indexreader
            indexSearcher.getIndexReader().close();*/
/*
        int insert = unitService.insert("sds", "static/images/baby.gif", "fdsaf", "222", "fkdnmkg", "fdsgrerr");
        System.out.println(insert);*/

//       creat c=new creat();
//        c.create("我的简介", "大家好,我叫小铭,我的专业是网络工程");
//        c.create("我的专业", "我的专业是网络工程");


    }

}
