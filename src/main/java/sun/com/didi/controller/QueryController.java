package sun.com.didi.controller;


import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.com.didi.entity.Recruit;
import sun.com.didi.service.RecruitServiceImpl;

import java.io.File;
import java.io.IOException;

@RestController
public class QueryController {
    @Autowired
    private RecruitServiceImpl recruitService;
    @RequestMapping(value = "/Query",method = RequestMethod.GET)
    public String Lucene(){return "/Lucene";}
    @PostMapping(value = "/Recruit")
    @ResponseBody
    /*index.indexOf(String.valueOf(topDocs.scoreDocs))>0*/
    public void doc(String str) throws IOException {
        Recruit byCompany = recruitService.findByCompany(str);
        if (str.indexOf(byCompany.getRec_company())!=-1){
            Query(str);
        }
    }
    public TopDocs Query(String index) throws IOException {
        //lucene搜索引擎
        Directory directory = FSDirectory.open(new File("src/index").toPath());
        IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        //创建查询对象
        Query query = new TermQuery(new Term("Rec_company", index));
        //执行查询
        TopDocs topDocs = indexSearcher.search(query, 2);
        //共查询到的document个数
        System.out.println("查询结果总数量：" + topDocs.totalHits);
        //遍历查询结果
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            Document document = indexSearcher.doc(scoreDoc.doc);
            System.out.println(document.get("Rec_company"));
            System.out.println(document.get("Rec_logo"));
            System.out.println(document.get("Rec_category"));
            System.out.println(document.get("Rec_salary"));
            System.out.println(document.get("Rec_Duration"));
            System.out.println(document.get("Rec_experience"));

        }
        //关闭indexreader
        indexSearcher.getIndexReader().close();
        return topDocs;
    }

}
