package com.example.tldr.models;

/**
 * Created by AnirudhKaushik on 2/9/19.
 */
public class TrendingTopic {
  private Integer id;
  private String topicTitle;

  public TrendingTopic(Integer id, String topicTitle) {
    this.id = id;
    this.topicTitle = topicTitle;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTopicTitle() {
    return topicTitle;
  }

  public void setTopicTitle(String topicTitle) {
    this.topicTitle = topicTitle;
  }
}
