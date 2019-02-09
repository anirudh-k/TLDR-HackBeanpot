package com.example.tldr;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

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
//    JSONParser parser = new JSONParser();
//    JSONObject jsonObject = (JSONObject) parser.parse(
//            new InputStreamReader(response, "UTF-8"));
//    JSONArray articles = (JSONArray) jsonObject.get("articles");

//    for (Object a : articles) {
//      JSONObject aObj = (JSONObject) a;
//      System.out.println(aObj.get("title"));
//    }

    try (Scanner scanner = new Scanner(response)) {
      String responseBody = scanner.useDelimiter("\\A").next();
      System.out.println(responseBody);
    }
  }
}
