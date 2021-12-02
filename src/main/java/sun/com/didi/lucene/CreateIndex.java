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
import sun.com.didi.entity.Recruit;
import sun.com.didi.service.SelectRecruitment;

import java.io.File;
import java.util.List;

@Component
public class CreateIndex {
    @Autowired
    private SelectRecruitment selectRecruitment;

//    @PostConstruct()
    public void create() throws Exception {
//1.创建一个Director对象，指定索引库保存的位置。
        //把索引库保存在内存中
        //Directory directory = new RAMDirectory();
        //把索引库保存在磁盘
        Directory directory = FSDirectory.open(new File("src/index").toPath());

        //2.基于Directory对象创建一个IndexWriter对象,默认使用StandardAnalyzer分析器
        //IndexWriterConfig config = new IndexWriterConfig();
        IndexWriterConfig config = new IndexWriterConfig(new IKAnalyzer());
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        IndexWriter indexWriter = new IndexWriter(directory, config);

        //3.创建一个Document对象
        Document document = new Document();
        //向document对象中添加域
        List<Recruit> byUserId = selectRecruitment.selectrecruitment();
        for (int i=0;i< byUserId.size();i++){
            System.out.println(byUserId.get(i).getRec_company());
            System.out.println(byUserId.get(i).getRec_category());
            System.out.println(byUserId.get(i).getRec_experience());
            System.out.println(byUserId.get(i).getRec_Duration());
            System.out.println(byUserId.get(i).getRec_salary());
           // System.out.println(byUserId.get(i).getRec_company());
            document.add(new TextField("Rec_company", byUserId.get(i).getRec_company(), Field.Store.YES));
            //document.add(new StringField("Rec_category",byUserId.get(i).getRec_category(), Field.Store.YES));
//            document.add(new TextField("Rec_logo",byUserId.get(i).getRec_logo(), Field.Store.YES));
//            document.add(new StringField("Rec_category",byUserId.get(i).getRec_category(), Field.Store.YES));
//            document.add(new StringField("Rec_experience", byUserId.get(i).getRec_experience(), Field.Store.YES));
//            document.add(new StringField("Rec_Duration",byUserId.get(i).getRec_Duration(),Field.Store.YES));
//            document.add(new StringField("Rec_salary",byUserId.get(i).getRec_salary(),Field.Store.YES));
        }

//        document.add(new Field("price",result, Store.YES));
        //4.把文档写入索引库
        indexWriter.addDocument(document);
        indexWriter.commit();
        //5.关闭索引库
         indexWriter.close();
    }
}

