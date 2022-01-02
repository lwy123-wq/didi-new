package sun.com.didi.controller;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.com.didi.service.RecruitServiceImpl;
import sun.com.didi.util.EsClient;

import javax.annotation.Resource;
import java.io.IOException;

@Controller
public class EsQueryController {
        @PostMapping(value = "/ES")
        @ResponseBody
    public void MatchAllQuery() throws IOException {

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
        System.out.println(resp.getHits().getHits().length);

    }
}
