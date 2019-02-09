package com.example.tldr.repositories;

import com.example.tldr.models.TrendingTopic;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by AnirudhKaushik on 2/9/19.
 */
public interface TrendingTopicRepository
        extends CrudRepository<TrendingTopic, Integer> {
}
