package com.example.tldr.services;

import com.example.tldr.models.Label;
import com.example.tldr.repositories.LabelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by AnirudhKaushik on 2/9/19.
 */
@RestController
public class LabelService {
  @Autowired
  LabelRepository labelRepository;

  @GetMapping("/api/labels")
  public List<Label> findAllLabels() {
    return labelRepository.findAllLabels();
  }

  @GetMapping("/api/labels/{id}")
  public List<Label> findLabelById(
          @PathVariable("id") Integer id) {
    return labelRepository.findLabelsById(id);
  }

  @PostMapping("/api/labels")
  public Label createLabel(
          @RequestBody Label label) {
    return labelRepository.save(label);
  }

  @DeleteMapping("/api/labels/{id}")
  public void deleteLabel(
          @PathVariable("id") Integer id) {
    labelRepository.deleteById(id);
  }
}
