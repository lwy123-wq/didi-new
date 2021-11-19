package sun.com.didi.lucene;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.NumericUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wltea.analyzer.lucene.IKAnalyzer;
import sun.com.didi.entity.Requirement;
import sun.com.didi.service.SelectRecruitment;
import sun.com.didi.service.WorkServiceImpl;

import javax.annotation.PostConstruct;
import java.io.File;
@Component
public class createIndex {
    @Autowired
    private WorkServiceImpl workService;
    @Autowired
    private SelectRecruitment selectRecruitment;
    @PostConstruct()
    public void create() throws Exception {
//1.创建一个Director对象，指定索引库保存的位置。
        //把索引库保存在内存中
        //Directory directory = new RAMDirectory();
        //把索引库保存在磁盘
        Directory directory = FSDirectory.open(new File("/home/lxj/文档/index").toPath());

        //2.基于Directory对象创建一个IndexWriter对象,默认使用StandardAnalyzer分析器
        //IndexWriterConfig config = new IndexWriterConfig();
        IndexWriterConfig config = new IndexWriterConfig(new IKAnalyzer());
        IndexWriter indexWriter = new IndexWriter(directory, config);

        //3.创建一个Document对象
        Document document = new Document();
        //向document对象中添加域
        Requirement byUserId =selectRecruitment.selectrecruitment();
        System.out.println(byUserId.getCompany());
        System.out.println(byUserId.getNumber());
        System.out.println(byUserId.getOccupationalCategory());
        System.out.println(byUserId.getRemainingPositions());
        System.out.println(byUserId.getSalaryRequirements());
//        document.add(new Field("price",result, Store.YES));
        document.add(new TextField("company", byUserId.getCompany(), Field.Store.YES));
        document.add(new TextField("OccupationalCategory", byUserId.getOccupationalCategory(), Field.Store.YES));
        document.add(new TextField("RemainingPositions", byUserId.getRemainingPositions(), Field.Store.YES));
        document.add(new TextField("SalaryRequirements", byUserId.getSalaryRequirements(), Field.Store.YES));
        document.add(new TextField("Number",byUserId.getNumber(), Field.Store.YES));
        /*document.add(new TextField("filename", "Lucene 简介 Lucene 是一个基于 Java 的全文信息检索工具包,\" +\n" +
                "                                                       \"它不是一个完整的搜索应用程序,而是为你的应用程序提供索引和搜索功能。", Field.Store.YES));*/
        //4.把文档写入索引库
        indexWriter.addDocument(document);
        indexWriter.commit();
        //5.关闭索引库
        // indexWriter.close();


    }
}

