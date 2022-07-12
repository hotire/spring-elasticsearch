package com.github.hotire.springelastic.ex;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import com.github.hotire.springelastic.ex.Article;
import com.github.hotire.springelastic.ex.ArticleRepository;

@SpringBootTest
class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ElasticsearchTemplate esTemplate;

    @BeforeEach
    void config() {
        esTemplate.deleteIndex(Article.class);
        esTemplate.createIndex(Article.class);
        esTemplate.putMapping(Article.class);
        esTemplate.refresh(Article.class);

        articleRepository.save(new Article("test"));
    }

    @Test
    void save() {
        // given
        final String title = "hello";
        final Article article = new Article(title);

        // when
        final Article saved = articleRepository.save(article);

        // then
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getTitle()).isEqualTo(title);
    }


    @Test
    void find() {
        // given
        final String title = "test";

        // when
        final List<Article> result = articleRepository.findAllByTitle(title);

        // then
       assertThat(result.size()).isEqualTo(1);
       assertThat(result.get(0).getTitle()).isEqualTo(title);
    }

}