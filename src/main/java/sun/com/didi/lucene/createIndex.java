package sun.com.didi.lucene;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wltea.analyzer.lucene.IKAnalyzer;
import sun.com.didi.entity.Requirement;
import sun.com.didi.service.SelectRecruitment;
import sun.com.didi.service.WorkServiceImpl;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;

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
        List<Requirement> byUserId = selectRecruitment.selectrecruitment();
        for (int i=0;i< byUserId.size();i++){
      /*      System.out.println(byUserId.get(i).getCompany());
            System.out.println(byUserId.get(i).getOccupationalCategory());
            System.out.println(byUserId.get(i).getRemainingPositions());
            System.out.println(byUserId.get(i).getSalaryRequirements());
            System.out.println(byUserId.get(i).getNumber());*/
            document.add(new TextField("company", byUserId.get(i).getCompany(), Field.Store.YES));
            document.add(new TextField("OccupationalCategory",byUserId.get(i).getOccupationalCategory(), Field.Store.YES));
            document.add(new TextField("RemainingPositions",byUserId.get(i).getRemainingPositions(), Field.Store.YES));
            document.add(new TextField("SalaryRequirements", byUserId.get(i).getSalaryRequirements(), Field.Store.YES));
            document.add(new TextField("Number", byUserId.get(i).getNumber(), Field.Store.YES));
        }

//        document.add(new Field("price",result, Store.YES));
        //4.把文档写入索引库
        indexWriter.addDocument(document);
        indexWriter.commit();
        //5.关闭索引库
        // indexWriter.close();
    }
}

