package sun.com.didi.controller;


import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wltea.analyzer.lucene.IKAnalyzer;
import sun.com.didi.service.RecruitServiceImpl;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class QueryController {
    @Autowired
    private RecruitServiceImpl recruitService;

    /* @RequestMapping(value = "/Lucene",method = RequestMethod.GET)
     public String Lucene(){return "/Lucene";}*/
    @PostMapping(value = "/query")
    @ResponseBody
    public List<Map<String, Object>> query(@RequestBody String str) throws IOException, ParseException {
        List<Map<String, Object>> list = null;
        String query1 = URLDecoder.decode(str, "utf-8");
        String stri[] = query1.split("=");
        String query = stri[1];
        System.out.println(query + "aaaaaaaaaaaaaaaaaaaaaaa");
/*        Recruit byCompany = recruitService.findCompany();
        Recruit byCategory = recruitService.findByCategory();
        System.out.println(byCategory.getRec_category()+"vvvvvvvvvvvvvv");
        int i = query.indexOf(byCompany.getRec_company());
        int i1 = query.indexOf(byCategory.getRec_category());*/
        list = QueryCompany(query, "Rec_company");


        return list;
    }

    public List<Map<String, Object>> QueryCompany(String index, String num) throws IOException, ParseException {
        //lucene????????????
        Directory directory = FSDirectory.open(new File("src/index").toPath());
        IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        QueryParser parser = new QueryParser(num, new IKAnalyzer());
        // ????????????text?????????
        Query query = parser.parse(index);
        //??????????????????
        // Query query = new TermQuery(new Term(num, index));
        //????????????
        TopDocs topDocs = indexSearcher.search(query, 2);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        //???????????????document??????
        System.out.println("????????????????????????" + topDocs.totalHits);
        // ?????????????????????
        for (ScoreDoc sd : topDocs.scoreDocs) {
            // ??????title
            String title = indexSearcher.doc(sd.doc).get(num);
            // ??????content
          //  String content = indexSearcher.doc(sd.doc).get(num);
            HashMap map = new HashMap<String, Object>();
            map.put(num, title);
            list.add(map);
            System.out.println(list.get(0));
            //??????????????????
       /* for (ScoreDoc scoreDoc : scoreDocs) {
            Document document = indexSearcher.doc(scoreDoc.doc);
            list.add(document.get("Rec_company"));
            System.out.println(document.get("Rec_company"));
            *//*System.out.println(document.get("Rec_company"));
            System.out.println(document.get("Rec_logo"));
            System.out.println(document.get("Rec_category"));
            System.out.println(document.get("Rec_salary"));
            System.out.println(document.get("Rec_Duration"));
            System.out.println(document.get("Rec_experience"));*//*


        }*/
            //??????indexreader
            indexSearcher.getIndexReader().close();
            return list;
        }
/*    public ArrayList<String> QueryCategory(String index) throws IOException {
        ArrayList<String> list=new ArrayList<>();
        //lucene????????????
        Directory directory = FSDirectory.open(new File("src/index").toPath());
        IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        //??????????????????
        Query query = new TermQuery(new Term("Rec_company", index));
        //????????????
        TopDocs topDocs = indexSearcher.search(query, 5);
        //???????????????document??????
        System.out.println("????????????????????????" + topDocs.totalHits);
        //??????????????????
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            Document document = indexSearcher.doc(scoreDoc.doc);
            list.add(document.get("Rec_company"));
            System.out.println(document.get("Rec_company"));
            System.out.println(document.get("Rec_logo"));
            System.out.println(document.get("Rec_category"));
            System.out.println(document.get("Rec_salary"));
            System.out.println(document.get("Rec_Duration"));
            System.out.println(document.get("Rec_experience"));

        }
        //??????indexreader
        indexSearcher.getIndexReader().close();
        return list;
    }*/
return list;
    }
}
