
import org.apache.commons.io.FileUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntPoint;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.NumericUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.wltea.analyzer.lucene.IKAnalyzer;
import sun.com.didi.Application;
import sun.com.didi.dao.JobDao;

import sun.com.didi.entity.Requirement;
import sun.com.didi.service.IntentionImpl;
import sun.com.didi.service.UserServiceImpl;
import sun.com.didi.service.WorkServiceImpl;

import java.io.File;

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
        //1、创建一个Director对象，指定索引库保存的位置。

        //1.1把索引库保存在内存中
        //Directory directory = new RAMDirectory();

        //1.2把索引库保存在磁盘
        Directory directory = FSDirectory.open(new File("/home/lxj/文档/index").toPath());

        //2、基于Directory对象创建一个IndexWriter对象
        //IndexWriterConfig config = new IndexWriterConfig();
        //当使用IKAnalyzer分词时，是如下写法。
        IndexWriterConfig config = new IndexWriterConfig(new IKAnalyzer());
        IndexWriter indexWriter = new IndexWriter(directory, config);

        //3、读取磁盘上的文件，对应每个文件创建一个文档对象。
        File dir = new File("/home/lxj/文档/index/document");
        File[] files = dir.listFiles();
        for (File f : files) {
            //取文件名
            String fileName = f.getName();
            //文件的路径
            String filePath = f.getPath();
            //文件的内容
            String fileContent = FileUtils.readFileToString(f, "utf-8");
            //文件的大小
            long fileSize = FileUtils.sizeOf(f);
            //创建Field
            //参数1：域的名称，参数2：域的内容，参数3：是否存储
            Field fieldName = new TextField("name", fileName, Field.Store.YES);
            Field fieldPath = new TextField("path", filePath, Field.Store.YES);
            Field fieldContent = new TextField("content", fileContent, Field.Store.YES);
            Field fieldSize = new TextField("size", fileSize + "", Field.Store.YES);

            //创建文档对象
            Document document = new Document();
            //向文档对象中添加域
            document.add(fieldName);
            document.add(fieldPath);
            document.add(fieldContent);
            document.add(fieldSize);

            //向document对象中添加域
            int price = 2999900;
            // Field 类有整数类型值的构造方法吗？
            // 用字节数组来存储试试，还是转为字符串？
            byte[] result = new byte[Integer.BYTES];
            NumericUtils.intToSortableBytes(price, result, 0);
            Requirement byUserId = workService.selectWork(1);
            System.out.println(byUserId.getCompany());
//        document.add(new Field("price",result, Store.YES));
            document.add(new TextField("recruitmentrequirements", byUserId.getCompany(), Field.Store.YES));
            document.add(new TextField("recruitmentrequirements", byUserId.getOccupationalCategory(), Field.Store.YES));
            document.add(new TextField("recruitmentrequirements", byUserId.getRemainingPositions(), Field.Store.YES));
            document.add(new TextField("recruitmentrequirements", byUserId.getSalaryRequirements(), Field.Store.YES));
            document.add(new IntPoint("recruitmentrequirements",byUserId.getNumber()));
            //4.把文档写入索引库
            indexWriter.addDocument(document);
            //5、把文档对象写入索引库
            indexWriter.addDocument(document);
        }

        //6、关闭indexwriter对象
        indexWriter.close();

    }

}
