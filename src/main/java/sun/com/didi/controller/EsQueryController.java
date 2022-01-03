package sun.com.didi.controller;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.com.didi.util.EsClient;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

@Controller
public class EsQueryController {
        @PostMapping(value = "/ES")
        @ResponseBody
    public Set<Map.Entry<String, Object>> MatchAllQuery() throws IOException {
            RestHighLevelClient client= EsClient.getClient();
      /*   matchAllQuery()   match查询
       RestHighLevelClient client= EsClient.getClient();
        SearchRequest request=new SearchRequest("recruit");
        // request.types(type);


        //指定查询条件
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.query(QueryBuilders.matchAllQuery());

        request.source(builder);

        //执行查询

        SearchResponse resp=client.search(request, RequestOptions.DEFAULT);

        //输出

        for (SearchHit hit: resp.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
        System.out.println(resp.getHits().getHits().length);*/

            Map<String, Object> map=null;
            //创建对象
            SearchRequest searchRequest = new SearchRequest("recruit");//指定索引
            //  searchRequest.types(type);//指定类型

            //        2.指定查询条件(先用searchSourceBuilder,再searchrequest指定searchSourceBuilder )
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.from(0);
            searchSourceBuilder.size(20);
            searchSourceBuilder.query(QueryBuilders.multiMatchQuery("天津盛和集团有限公司","rec_company"));

            System.out.println(searchRequest.source(searchSourceBuilder));

            //执行查询
            SearchResponse resp = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = resp.getHits().getHits();//从kibana里的结果可以看出来有两个hits
            for (SearchHit searchHit : hits){
                System.out.println(searchHit.getScore());
                System.out.println(searchHit.getIndex());
                map = searchHit.getSourceAsMap();
                System.out.println(searchHit.getId());
                for (Map.Entry<String,Object> s:map.entrySet()){
                    System.out.println(s.getKey()+"==========="+s.getValue());

                }
                System.out.println("_----------------------");
            }
            return map.entrySet();
    }
}
