package com.example.tldr.services;

import com.example.tldr.models.TrendingTopic;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnirudhKaushik on 2/9/19.
 */
@RestController
public class TrendingTopicService {
  static List<TrendingTopic> trendingTopics = new ArrayList<>();
  static {
    trendingTopics.add(new TrendingTopic(0, "State of the Union"));
    trendingTopics.add(new TrendingTopic(1, "Green New Deal"));
    trendingTopics.add(new TrendingTopic(2, "Ariana Grande Album"));
  }

  @GetMapping("/api/trendingtopics")
  public List<TrendingTopic> findAllTrendingTopics(){
    return trendingTopics;
  }

  @GetMapping("api/trendingtopics/{id}")
  public TrendingTopic findTrendingTopicById(
          @PathVariable("id") Integer id) {
    for (TrendingTopic topic : trendingTopics) {
      if (topic.getId() == id) {
        return topic;
      }
    }
    return null;
  }
}
