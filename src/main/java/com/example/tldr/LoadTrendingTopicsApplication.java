package com.example.tldr;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Created by AnirudhKaushik on 2/9/19.
 */
public class LoadTrendingTopicsApplication {

  public static void main(String[] args) throws IOException, ParseException {
    String url = "https://newsapi.org/v2/top-headlines";
    String charset = StandardCharsets.UTF_8.name();
    String country = "us";
    String apikey = "d6b8671535f54dcdbd42c554e051a7ad";

    String query = String.format("country=%s&apiKey=%s",
            URLEncoder.encode(country, charset),
            URLEncoder.encode(apikey, charset));

//    URLConnection connection = new URL(url + "?" + query).openConnection();
//    connection.setRequestProperty("Accept-Charset", charset);
//    InputStream response = connection.getInputStream();
    InputStream response = new URL(url + "?" + query).openStream();
    JSONParser parser = new JSONParser();
    JSONObject jsonObject = (JSONObject) parser.parse(
            new InputStreamReader(response, "UTF-8"));
    JSONArray articles = (JSONArray) jsonObject.get("articles");

    for (Object a : articles) {
      JSONObject aObj = (JSONObject) a;
      String source = (String) ((JSONObject) aObj.get("source")).get("name");
      String title = (String) aObj.get("title");
      String publishedAt = (String) aObj.get("publishedAt");
      String articleUrl = (String) aObj.get("url");

      deleteTrendingTopics();
      postToDatabase(source, title, publishedAt, articleUrl);
    }
  }

  private static void deleteTrendingTopics() throws IOException {
    String deleteUrl = "http://tldr-hackbeanpot.herokuapp.com/api/trendingtopics";

    HttpClient client = new DefaultHttpClient();
    HttpDelete request = new HttpDelete(deleteUrl);

    HttpResponse response = client.execute(request);
    System.out.println(response.getStatusLine().getStatusCode());
  }

  private static void postToDatabase(String source, String title, String publishedAt, String url) throws IOException {
    String postUrl = "http://tldr-hackbeanpot.herokuapp.com/api/articles";

    HttpClient client = new DefaultHttpClient();
    HttpPost request = new HttpPost(postUrl);
    StringEntity params = new StringEntity("{\"source\":\"" + source + "\"," +
            "\"title\":\"" + title + "\"," +
            "\"publishedAt\":\"" + publishedAt + "\"," +
            "\"url\":\"" + url + "\"}", ContentType.APPLICATION_JSON);
    request.setHeader("Content-Type", "application/json");
    request.setEntity(params);

    HttpResponse response = client.execute(request);
    System.out.println(response.getStatusLine().getStatusCode());
  }
}
