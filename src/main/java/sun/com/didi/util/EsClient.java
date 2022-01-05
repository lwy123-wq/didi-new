package sun.com.didi.util;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

public class EsClient {
    public static RestHighLevelClient getClient(){

        //创建HttpHost对象
        HttpHost httpHost=new HttpHost("192.168.1.4",9200);
        //创建RestClientBuilder
        RestClientBuilder clientBuilder= RestClient.builder(httpHost);
        //创建RestHighLevelClient
        RestHighLevelClient client=new RestHighLevelClient(clientBuilder);
        return client;
    }
}
