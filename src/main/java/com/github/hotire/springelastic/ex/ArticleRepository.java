package com.github.hotire.springelastic.ex;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ArticleRepository extends ElasticsearchRepository<Article, String> {

    List<Article> findAllByTitle(String name);
//
//    @Query("{\"bool\": {\"must\": [{\"match\": {\"authors.name\": \"?0\"}}]}}")
//    Page<Article> findByAuthorsNameUsingCustomQuery(String name, Pageable pageable);
}

