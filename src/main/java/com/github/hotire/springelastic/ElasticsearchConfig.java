package com.github.hotire.springelastic;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories(basePackageClasses = ElasticsearchConfig.class)
@Configuration
public class ElasticsearchConfig {
}
