package com.example.tldr;

import java.io.*;

import com.aylien.textapi.TextAPIClient;
import com.aylien.textapi.parameters.SummarizeParams;
import com.aylien.textapi.responses.Summarize;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class SummarizeEngine {
  public static void main(String[] args) throws IOException {
    List<File> articles = new ArrayList<>();
    File file = new File(" /Users/Skylar.Gilfeather/Desktop/Article1Right.txt");
    articles.add(file);
    File file2 = new File("/Users/Skylar.Gilfeather/Desktop/Article2Left.txt");
    articles.add(file2);
    StringBuilder finalart = new StringBuilder();
    for (File k : articles)
      finalart.append(buildString(k));
    String[] master = sendToSMMRY(finalart.toString());
    for (String k : master)
      System.out.println(k);
  }
  private static String buildString(File filey) throws IOException {
    Path path = Paths.get(filey.getName()); //Change path later on
    List<String> lines = Files.lines(path, StandardCharsets.UTF_16).collect(Collectors.toList());
    StringBuilder chain = new StringBuilder();
    for (String k : lines) {
      chain.append(k);
      chain.append("\n");
    }
    return chain.toString();
  }
  private static String[] sendToSMMRY(String file){
    while (true) {
      try {
        TextAPIClient client = new TextAPIClient("4a89743b", "917adffd798d1e6ca08a2f1ca2fe5e0e");
        SummarizeParams.Builder builder = SummarizeParams.newBuilder();
        builder.setText(file);
        String title = file.substring(file.indexOf("a"));
        builder.setTitle(title);
        builder.setNumberOfSentences(5);
        Summarize summary = client.summarize(builder.build());
        return summary.getSentences();
      }
      catch (Exception exception){
        continue;
      }
    }
  }
}
