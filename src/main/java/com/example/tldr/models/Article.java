package com.example.tldr.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by AnirudhKaushik on 2/9/19.
 */
@Entity
@Table(name = "article")
public class Article {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String source;
  private String title;
  @Column(name = "published_at")
  private String publishedAt;
  private String url;

  public Article() {}

  public Article(Integer id, String source, String title, String publishedAt, String url) {
    this.id = id;
    this.source = source;
    this.title = title;
    this.publishedAt = publishedAt;
    this.url = url;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPublishedAt() {
    return publishedAt;
  }

  public void setPublishedAt(String publishedAt) {
    this.publishedAt = publishedAt;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Article) {
      Article articleObj = (Article) obj;
      return id == articleObj.id;
    }
    return false;
  }
}
