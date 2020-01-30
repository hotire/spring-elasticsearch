package com.github.hotire.springelastic;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Document(indexName = "hotire", type = "article")
public class Article {

    @Id
    private String id;

    private String title;

    public Article(String title) {
        this.title = title;
    }

}
