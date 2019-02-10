package com.example.tldr.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
  String keyword1;
  String keyword2;
  String keyword3;
  String keyword4;
  String keyword5;

  public Label() {}

  public Label(Integer articleId, String category, String keyword1, String keyword2, String keyword3, String keyword4, String keyword5) {
    this.articleId = articleId;
    this.category = category;
    this.keyword1 = keyword1;
    this.keyword2 = keyword2;
    this.keyword3 = keyword3;
    this.keyword4 = keyword4;
    this.keyword5 = keyword5;
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

  public String getKeyword1() {
    return keyword1;
  }

  public void setKeyword1(String keyword1) {
    this.keyword1 = keyword1;
  }

  public String getKeyword2() {
    return keyword2;
  }

  public void setKeyword2(String keyword2) {
    this.keyword2 = keyword2;
  }

  public String getKeyword3() {
    return keyword3;
  }

  public void setKeyword3(String keyword3) {
    this.keyword3 = keyword3;
  }

  public String getKeyword4() {
    return keyword4;
  }

  public void setKeyword4(String keyword4) {
    this.keyword4 = keyword4;
  }

  public String getKeyword5() {
    return keyword5;
  }

  public void setKeyword5(String keyword5) {
    this.keyword5 = keyword5;
  }
}
