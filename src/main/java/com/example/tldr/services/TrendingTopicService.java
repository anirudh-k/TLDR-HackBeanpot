package com.example.tldr.services;

import com.example.tldr.models.TrendingTopic;
import com.example.tldr.repositories.TrendingTopicRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by AnirudhKaushik on 2/9/19.
 */
@RestController
public class TrendingTopicService {
  @Autowired
  TrendingTopicRepository trendingTopicRepository;

  @GetMapping("/api/trendingtopics")
  public Iterable<TrendingTopic> findAllTrendingTopics(){
    return trendingTopicRepository.findAll();
  }

  @GetMapping("/api/trendingtopics/{id}")
  public TrendingTopic findTrendingTopicById(
          @PathVariable("id") Integer id) {
    for (TrendingTopic topic : trendingTopicRepository.findAll()) {
      if (topic.getId() == id) {
        return topic;
      }
    }
    return null;
  }

  @PostMapping("/api/trendingtopics")
  public TrendingTopic createTrendingTopic(
          @RequestBody TrendingTopic trendingTopic) {
    return trendingTopicRepository.save(trendingTopic);
  }
}
