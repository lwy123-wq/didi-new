import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sun.com.didi.Application;
import sun.com.didi.controller.DetailedController;
import sun.com.didi.controller.HostDataController;
import sun.com.didi.dao.CoordinateDao;
import sun.com.didi.dao.JobDao;
import sun.com.didi.dao.LoginDao;
import sun.com.didi.dao.RecruitDao;
import sun.com.didi.entity.Login;
import sun.com.didi.entity.Recruit;
import sun.com.didi.entity.Report;
import sun.com.didi.service.*;
import sun.com.didi.util.LruCacheUtil;

import javax.annotation.Resource;
import java.util.List;
import java.util.RandomAccess;

@SpringBootTest(classes = Application.class)
public class test {
    @Autowired
    private JobDao jobDao;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private IntentionImpl intention;
    @Resource
    private RecruitServiceImpl unitService;
    @Autowired
    private TrieTreeImpl trieTre;
    @Autowired
    private LruService lruService;
    @Autowired
    private DetailedController detailedController;
    @Autowired
    private CoordinateDao coordinateDao;
    @Autowired
    private LoginDao loginDao;
    @Autowired
    private RecruitDao recruitDao;
    @Autowired
    private JobServiceImpl jobService;
    @Autowired
    private RunTimeServiceImpl runTimeService;
    private static int sum1=1;
    private static int sum2=1;
    private static int sum3=3;
    private static int sum4=4;
    @Test
    public void t() throws Exception {
        //jobService.select
       // System.out.println(jobService.SelectJob("lwy"));
        //System.out.println(loginDao.insertToken("1", "lxj"));
       /* System.out.println(coordinateDao.selectNode());
        System.out.println(unitService.select());
        System.out.println(coordinateDao.selectCoordinate(104.195345, 35.86133));*/
     /*   Recruit aa=unitService.FindByCompany("天津娃哈哈有限公司");
        String bb=aa.getRec_job();
        System.out.println(bb+"aaaaaaaaaaaa");
        int cc=Integer.parseInt(bb);
        int dd=cc-1;
        String ff=Integer.toString(dd);
        System.out.println(unitService.update(ff,"天津娃哈哈有限公司"));*/



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
        /*List<String> list=trieTre.search("天津");
        System.out.println(list);*/
     /*   Login login=new Login("李小军","23425","4545@qq.com");
        Object cache = hostDataController.lru(login);*/

        /*LruCacheUtil xxx = lruService.methods("京东");

            xxx.get(sum1);    // 1:a
            System.out.println(xxx.toString());
            sum1++;

        LruCacheUtil yyy = lruService.methods("百度");
           yyy.get(sum1);
            System.out.println(yyy.toString());
        sum1++;

        LruCacheUtil zz = lruService.methods("xxx");
        zz.get(sum1);
        System.out.println(zz.toString());
        sum1++;
        LruCacheUtil eee = lruService.methods("kk");
        eee.get(sum1);
        System.out.println(eee.toString());
        sum1++;

        LruCacheUtil eee1 = lruService.methods("eee");
        eee1.get(sum1);
        System.out.println(eee1.toString());
        sum1++;*/
/*        LruCacheUtil yyy = lruService.methods("百度");
        for (int i=1;i<=2;i++){
                System.out.println(yyy.get(sum2));
                sum2++;
        }
        LruCacheUtil sss = lruService.methods("aa");
        for (int i=1;i<=2;i++){
            System.out.println(sss.get(sum3));
            sum3--;
        }
        LruCacheUtil zzz = lruService.methods("zzz");
        for (int i=1;i<=2;i++){
            System.out.println(zzz.get(sum4));
            sum4--;
        }*/

        Report runtime = runTimeService.runtime("张思慧");
        String utcTime = runtime.getTime();
        String kk[] = utcTime.split("年");
        String stri = utcTime.substring(1);
        System.out.println(utcTime.substring(1).equals("年"));
        System.out.println(stri);
        if (stri.equals("年")){
            int time = Integer.parseInt(kk[0]);
           int z= time * 365;
            System.out.println(z);
        }

        //System.out.println(sss);
    /*    System.out.println(query);
        int time = Integer.parseInt(query);
        int s=time * 365;
        System.out.println(s);*/
    }

}
