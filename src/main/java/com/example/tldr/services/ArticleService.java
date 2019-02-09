package com.example.tldr.services;

import com.example.tldr.models.Article;
import com.example.tldr.repositories.ArticleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by AnirudhKaushik on 2/9/19.
 */
@RestController
public class ArticleService {
  @Autowired
  ArticleRepository articleRepository;

  @GetMapping("/api/articles")
  public List<Article> findAllArticles(){
    return articleRepository.findAllArticles();
  }

  @GetMapping("/api/articles/{id}")
  public Article findArticleById(
          @PathVariable("id") Integer id) {
    return articleRepository.findArticleById(id);
  }

  @PostMapping("/api/articles")
  public Article createArticle(
          @RequestBody Article article) {
    return articleRepository.save(article);
  }

  @PutMapping("/api/articles/{id}")
  public Article updateArticle(
          @PathVariable("id") Integer id,
          @RequestBody Article articleUpdates) {
    Article article = articleRepository.findArticleById(id);
    article.setSource(articleUpdates.getSource());
    article.setTitle(articleUpdates.getTitle());
    article.setPublishedAt(articleUpdates.getPublishedAt());
    article.setUrl(articleUpdates.getUrl());
    return articleRepository.save(article);
  }

  @DeleteMapping("/api/articles/{id}")
  public void deleteArticle(
          @PathVariable("id") Integer id) {
    articleRepository.deleteById(id);
  }
}
