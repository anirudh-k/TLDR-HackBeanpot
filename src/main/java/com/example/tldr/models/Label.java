package com.example.tldr.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by AnirudhKaushik on 2/9/19.
 */
@Entity
@Table(name = "label")
public class Label {
  @Id
  @Column(name = "article_id")
  Integer articleId;
  String category;
  Double confidence;

  public Label() {}

  public Label(Integer articleId, String category, Double confidence) {
    this.articleId = articleId;
    this.category = category;
    this.confidence = confidence;
  }

  public Integer getArticleId() {
    return articleId;
  }

  public void setArticleId(Integer articleId) {
    this.articleId = articleId;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public Double getConfidence() {
    return confidence;
  }

  public void setConfidence(Double confidence) {
    this.confidence = confidence;
  }
}
