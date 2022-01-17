package sun.com.didi.controller;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.com.didi.util.EsClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class Insert_position_EsController {
    @RequestMapping(value = "/insert_info",method = RequestMethod.GET)
    public String index(){
        return "insert_info";
    }
    @PostMapping(value = "/insert_ordered_Es")
    @ResponseBody
    //es 过滤字段
    public List<Map<String, Object>> insert_ordered_Es() throws IOException {
        List<Map<String, Object>> arrayList = new ArrayList<Map<String, Object>>();
        SearchRequest searchRequest = new SearchRequest("recruit");//指定索引
        RestHighLevelClient client = EsClient.getClient();
        SearchSourceBuilder builder=new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        String [] exclides={};//要排除的字段
        String [] includes={"rec_category"};//要查询的指定字段
        builder.fetchSource(includes,exclides);

        searchRequest.source(builder);

        SearchResponse response=client.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits hits=response.getHits();
        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());

        for (SearchHit hit: hits){
            System.out.println(hit.getSourceAsString());
            Map<String, Object> map =hit.getSourceAsMap();
            arrayList.add(map);
        }
        return arrayList;
    }
}
