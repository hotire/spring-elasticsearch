package com.github.hotire.springelastic.ex;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.NodeClientFactoryBean;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.util.Objects;


@EnableElasticsearchRepositories(basePackageClasses = ElasticsearchConfig.class)
@Configuration
public class ElasticsearchConfig {

    @Value("${elasticsearch.clustername}")
    private String esClusterName;

    @Bean
    public ElasticsearchOperations elasticsearchTemplate(NodeClientFactoryBean nodeClientFactoryBean) throws Exception {
        return new ElasticsearchTemplate(Objects.requireNonNull(nodeClientFactoryBean.getObject()));
    }

    //Embedded Elasticsearch Server
    @Bean
    public NodeClientFactoryBean nodeClientFactoryBean() {
        NodeClientFactoryBean nodeClientFactoryBean = new NodeClientFactoryBean(true);
        nodeClientFactoryBean.setPathData("target/elasticsearchTestData");
        nodeClientFactoryBean.setPathHome("src/test/resources/test-home-dir");
        nodeClientFactoryBean.setEnableHttp(false);
        nodeClientFactoryBean.setClusterName(esClusterName);
        return nodeClientFactoryBean;
    }
}
