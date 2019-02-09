package com.example.tldr.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by AnirudhKaushik on 2/9/19.
 */
@Entity
@Table(name = "trendingtopic")
public class TrendingTopic {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String title;

  public TrendingTopic() {}

  public TrendingTopic(Integer id, String title) {
    this.id = id;
    this.title = title;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof TrendingTopic) {
      TrendingTopic trendingTopic = (TrendingTopic) obj;
      return id == trendingTopic.id;
    }
    return false;
  }
}
