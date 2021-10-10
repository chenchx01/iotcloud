package com.iotcloud.common.elasticsearch.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchClientConfig {

    @Value("${elasticSearch.hosts}")
    public String hosts;
    @Value("${elasticSearch.ports}")
    public String ports;
    @Value("${elasticSearch.scheme}")
    public String scheme;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        String[] hostarr = hosts.split(",");
        String[] portarr = ports.split(",");
        HttpHost[] httpHosts = new HttpHost[hostarr.length];
        for (int i = 0; i < hostarr.length; i++) {
            httpHosts[i] =  new HttpHost(hostarr[i], Integer.parseInt(portarr[i]), scheme);
        }
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        httpHosts));

        return client;
    }
}