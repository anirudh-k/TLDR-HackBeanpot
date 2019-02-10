package com.example.tldr.services;

import com.example.tldr.models.Article;
import com.example.tldr.models.Label;
import com.example.tldr.repositories.ArticleRepository;
import com.example.tldr.repositories.LabelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by AnirudhKaushik on 2/9/19.
 */
@RestController
public class ArticleService {
  @Autowired
  ArticleRepository articleRepository;
  @Autowired
  LabelRepository labelRepository;

  @GetMapping("/api/articles")
  public List<Article> findAllArticles(){
    return articleRepository.findAllArticles();
  }

  @GetMapping("/api/articles/{id}")
  public Article findArticleById(
          @PathVariable("id") Integer id) {
    return articleRepository.findArticleById(id);
  }

  @GetMapping("/api/articles/{id}/related")
  public List<Article> findRelatedArticles(
          @PathVariable("id") Integer id) {
    Article source = articleRepository.findArticleById(id);
    List<Label> sourceLabels = labelRepository.findLabelsById(id);

    List<List<Label>> relatedLabelsBySourceCategory = new ArrayList<>();
    for (Label l : sourceLabels) {
      List<Label> sameCategory = new ArrayList<>();
      for (Label m : labelRepository.findLabelByCategory(l.getCategory())) {
        if (Math.abs(m.getConfidence() - l.getConfidence()) < 0.1) {
          sameCategory.add(m);
        }
      }
      relatedLabelsBySourceCategory.add(sameCategory);
    }
    List<Set<Article>> relatedArticlesByCategory = new ArrayList<>();
    for (List<Label> list : relatedLabelsBySourceCategory) {
      Set<Article> articlesWithLabel = new HashSet<>();
      for (Label l : list) {
        articlesWithLabel.add(articleRepository.findArticleById(l.getArticleId()));
      }
      relatedArticlesByCategory.add(articlesWithLabel);
    }
    if (relatedArticlesByCategory.size() > 0) {
      Set<Article> relatedArticles = new HashSet<>(relatedArticlesByCategory.get(0));
      for (int i = 1; i < relatedArticlesByCategory.size() - 1; i++) {
        relatedArticles.retainAll(relatedArticlesByCategory.get(i));
      }
      return new ArrayList<>(relatedArticles);
    }
    return new ArrayList<>();
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
    article.setBody(articleUpdates.getBody());
    return articleRepository.save(article);
  }

  @DeleteMapping("/api/articles/{id}")
  public void deleteArticle(
          @PathVariable("id") Integer id) {
    articleRepository.deleteById(id);
  }
}
