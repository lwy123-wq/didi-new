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
import sun.com.didi.service.WorkServiceImpl;

import java.io.File;
@Component
public class createIndex {
    @Autowired
    private static WorkServiceImpl workService;
    public static void create() throws Exception {
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
/*        int price = 2999900;
        // Field 类有整数类型值的构造方法吗？
        // 用字节数组来存储试试，还是转为字符串？
        byte[] result = new byte[Integer.BYTES];
        NumericUtils.intToSortableBytes(price, result, 0);*/
        Requirement byUserId = workService.selectWork(1);
//        document.add(new Field("price",result, Store.YES));
        document.add(new TextField("recruitmentrequirements", byUserId.getCompany(), Field.Store.YES));
        document.add(new TextField("recruitmentrequirements", byUserId.getOccupationalCategory(), Field.Store.YES));
        document.add(new TextField("recruitmentrequirements", byUserId.getRemainingPositions(), Field.Store.YES));
        document.add(new TextField("recruitmentrequirements", byUserId.getSalaryRequirements(), Field.Store.YES));
        document.add(new TextField("recruitmentrequirements",byUserId.getNumber(), Field.Store.YES));
        //4.把文档写入索引库
        indexWriter.addDocument(document);
        //5.关闭索引库
        // indexWriter.close();


    }
}
