package com.example.tldr.repositories;

import com.example.tldr.models.Article;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by AnirudhKaushik on 2/9/19.
 */
public interface ArticleRepository extends CrudRepository<Article, Integer> {
  @Query(value = "SELECT article FROM Article article")
  public List<Article> findAllArticles();
  @Query(value = "SELECT article FROM Article article WHERE article.id=:id")
  public Article findArticleById(@Param("id") Integer id);
}
