package sun.com.didi.controller;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.com.didi.filter.BloomFilter;
import sun.com.didi.service.BloomFilterService;
import sun.com.didi.util.EsClient;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class EsQueryController {
    @Resource
    private BloomFilter bloomFilter;
    @Autowired
    private BloomFilterService bloomFilterService;

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public String start(){
        return "search";
    }
    @PostMapping(value = "/MatchAllQuery")
    @ResponseBody
    public  List<Map<String, Object>> MatchAllQuery(@RequestBody String doc) throws IOException {

        boolean bloom=bloomFilterService.includeByBloomFilter(bloomFilter,doc,doc);
        if (bloom==true) {
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            String str = URLDecoder.decode(doc, "utf-8");
            String stri[] = str.split("=");
            String query = stri[1];
            SearchResponse resp;
            try (RestHighLevelClient client = EsClient.getClient()) {
                //创建对象
                SearchRequest searchRequest = new SearchRequest("recruit");//指定索引
                //  searchRequest.types(type);//指定类型
                //  2.指定查询条件(先用searchSourceBuilder,再searchrequest指定searchSourceBuilder )
                SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
                searchSourceBuilder.from(0);
                searchSourceBuilder.size(5);
                searchSourceBuilder.query(QueryBuilders.multiMatchQuery(query, "rec_company"));

                System.out.println(searchRequest.source(searchSourceBuilder));

                //执行查询
                resp = client.search(searchRequest, RequestOptions.DEFAULT);
            }
            SearchHits hits = resp.getHits();//从kibana里的结果可以看出来有两个hits
            for (SearchHit searchHit : hits) {
                Map<String, Object> map = searchHit.getSourceAsMap();
                list.add(map);
                // System.out.println("_----------------------"+map.entrySet());
            }
            return list;
        }else {
            return null;
        }
    }
}
