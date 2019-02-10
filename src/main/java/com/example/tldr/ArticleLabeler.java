package com.example.tldr;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;

/**
 * Created by AnirudhKaushik on 2/9/19.
 */
public class ArticleLabeler {
  public static void main(String[] args) throws IOException, ParseException {
    String url = "https://language.googleapis.com/v1/documents:classifyText?key=AIzaSyAeCSbWiyQhl6XQM5VvuCDlx4y7sFvq2AM";
    String content = "";

    HostnameVerifier hostnameVerifier = org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;

    HttpClient client = new DefaultHttpClient();

    SchemeRegistry registry = new SchemeRegistry();
    SSLSocketFactory socketFactory = SSLSocketFactory.getSocketFactory();
    socketFactory.setHostnameVerifier((X509HostnameVerifier) hostnameVerifier);
    registry.register(new Scheme("https", socketFactory, 443));
    SingleClientConnManager mgr = new SingleClientConnManager(client.getParams(), registry);

    HttpClient httpClient = new DefaultHttpClient(mgr, client.getParams());

    HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);

    HttpPost requestCategory = new HttpPost(url);
    StringEntity postParams = new StringEntity("{\"document\":" +
            "{\"type\":\"PLAIN_TEXT\"," +
            "\"content\":\"" + content + "\"}}",
            ContentType.APPLICATION_JSON);
    requestCategory.setHeader("Content-Type", "application/json");
    requestCategory.setEntity(postParams);
    HttpResponse response = httpClient.execute(requestCategory);
    JSONObject jsonResponse = (JSONObject) new JSONParser().parse(
            new InputStreamReader(response.getEntity().getContent(), "UTF-8")
    );
    System.out.println(jsonResponse);
    JSONArray cats = (JSONArray) jsonResponse.get("categories");
    List<String> categories = new ArrayList<>();
    for (Object o : cats) {
      JSONObject cat = (JSONObject) o;
      categories.add((String) cat.get("name"));
    }



//    client = new DefaultHttpClient();
//    String postTrendingUrl = "http://tldr-hackbeanpot.herokuapp.com/api/labels";
//
//    HttpPost requestTrending = new HttpPost(postTrendingUrl);
//    StringEntity trendingParams = new StringEntity("{\"title\":\"" + title + "\"," +
//            "\"url\":\"" + url + "\"}",
//            ContentType.APPLICATION_JSON);
//    requestTrending.setHeader("Content-Type", "application/json");
//    requestTrending.setEntity(trendingParams);
//    response = client.execute(requestTrending);
  }
}
