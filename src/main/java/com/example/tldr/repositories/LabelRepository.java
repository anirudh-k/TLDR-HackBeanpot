package com.example.tldr.repositories;

import com.example.tldr.models.Label;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by AnirudhKaushik on 2/9/19.
 */
public interface LabelRepository
        extends CrudRepository<Label, Integer> {
  @Query(value = "SELECT label FROM Label label")
  public List<Label> findAllLabels();
  @Query(value = "SELECT label FROM Label label WHERE label.id=:id")
  public Label findLabelById(@Param("id") Integer id);
}
