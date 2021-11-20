package sun.com.didi.controller;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
public class LuceneController {
    @PostMapping(value = "/LuceneQuery")
    @ResponseBody
    public String LuceneQuery(String index)throws Exception{
        Directory directory= FSDirectory.open(new File("../src/index").toPath());
        IndexReader indexReader= DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        //创建查询对象
        TermQuery query = new TermQuery(new Term("company", index));
        //执行查询
        TopDocs topDocs = indexSearcher.search(query, 1);
        //共查询到的document个数
        System.out.println("查询到的总数量："+topDocs.totalHits);
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
        indexSearcher.getIndexReader().close();


        return "查询成功！";
    }
}
