package com.example.tldr.services;

import com.example.tldr.LoadTrendingTopicsApplication;
import com.example.tldr.models.TrendingTopic;
import com.example.tldr.repositories.TrendingTopicRepository;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Created by AnirudhKaushik on 2/9/19.
 */
@RestController
public class TrendingTopicService {
  @Autowired
  TrendingTopicRepository trendingTopicRepository;

  @GetMapping("/api/trendingtopics")
  public List<TrendingTopic> findAllTrendingTopics() throws IOException, ParseException {
    List<TrendingTopic> trendingTopics = trendingTopicRepository.findAllTrendingTopics();
    if (trendingTopics.size() > 0) {
      return trendingTopicRepository.findAllTrendingTopics();
    }
    LoadTrendingTopicsApplication.main(new String[0]);
    return trendingTopicRepository.findAllTrendingTopics();
  }

  @GetMapping("/api/trendingtopics/{id}")
  public TrendingTopic findTrendingTopicById(
          @PathVariable("id") Integer id) {
    return trendingTopicRepository.findTrendingTopicById(id);
  }

  @PostMapping("/api/trendingtopics")
  public TrendingTopic createTrendingTopic(
          @RequestBody TrendingTopic trendingTopic) {
    return trendingTopicRepository.save(trendingTopic);
  }

  @PutMapping("/api/trendingtopics/{id}")
  public TrendingTopic updateTrendingTopic(
          @PathVariable("id") Integer id,
          @RequestBody TrendingTopic trendingTopicUpdates) {
    TrendingTopic trendingTopic = trendingTopicRepository.findTrendingTopicById(id);
    trendingTopic.setTitle(trendingTopicUpdates.getTitle());
    trendingTopic.setSummary(trendingTopicUpdates.getSummary());
    return trendingTopicRepository.save(trendingTopic);
  }

  @DeleteMapping("/api/trendingtopics/{id}")
  public void deleteTrendingTopic(
          @PathVariable("id") Integer id) {
    trendingTopicRepository.deleteById(id);
  }

  @DeleteMapping("/api/trendingtopics")
  public void deleteAll() {
    for (TrendingTopic topic : trendingTopicRepository.findAll()) {
      trendingTopicRepository.deleteById(topic.getId());
    }
  }
}
