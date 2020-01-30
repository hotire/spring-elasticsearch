package com.github.hotire.springelastic;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    public void save() {
        Article article = new Article("hello");
        Article saved = articleRepository.save(article);
        System.out.println(saved.getId());
        System.out.println(saved.getTitle());
    }


}