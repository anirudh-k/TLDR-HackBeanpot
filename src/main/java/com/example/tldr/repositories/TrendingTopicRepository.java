package com.example.tldr.repositories;

import com.example.tldr.models.TrendingTopic;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by AnirudhKaushik on 2/9/19.
 */
public interface TrendingTopicRepository
        extends CrudRepository<TrendingTopic, Integer> {
  @Query(value = "SELECT trendingtopic FROM TrendingTopic trendingtopic")
  public List<TrendingTopic> findAllTrendingTopics();
}
