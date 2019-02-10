package com.example.tldr;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
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
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;

/**
 * Created by AnirudhKaushik on 2/10/19.
 */
public class LoadArticles {
  private static final String CSV_FILE_PATH = "/Users/AnirudhKaushik/Documents/tldr/src/main/resources/static/articles/articles1.csv";
  private static final String POST_ARTICLE_URL = "https://tldr-hackbeanpot.herokuapp.com/api/articles";

  public static void main(String[] args) throws IOException, ParseException {
    Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
    CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);

    for (CSVRecord csvRecord : csvParser) {
      String source = csvRecord.get(3);
      String title = csvRecord.get(2);
      String publishedAt = csvRecord.get(5);
      String body = csvRecord.get(9);

      LoadArticles.postArticle(source, title, publishedAt, body);
    }
  }

  private static void postArticle(String source, String title, String publishedAt, String body) throws IOException, ParseException {
    HostnameVerifier hostnameVerifier = org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;

    HttpClient client = new DefaultHttpClient();

    SchemeRegistry registry = new SchemeRegistry();
    SSLSocketFactory socketFactory = SSLSocketFactory.getSocketFactory();
    socketFactory.setHostnameVerifier((X509HostnameVerifier) hostnameVerifier);
    registry.register(new Scheme("https", socketFactory, 443));
    SingleClientConnManager mgr = new SingleClientConnManager(client.getParams(), registry);

    HttpClient httpClient = new DefaultHttpClient(mgr, client.getParams());

    HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);

    HttpPost requestCategory = new HttpPost(POST_ARTICLE_URL);
    StringEntity postParams = new StringEntity("{\"source\":\"" + source + "\"," +
            "\"title\":\"" + title + "\"," +
            "\"publishedAt\":\"" + publishedAt + "\"," +
            "\"body\":\"" + body + "\"}",
            ContentType.APPLICATION_JSON);
    requestCategory.setHeader("Content-Type", "application/json");
    requestCategory.setEntity(postParams);
    HttpResponse response = httpClient.execute(requestCategory);
    JSONObject jsonResponse = (JSONObject) new JSONParser().parse(
            new InputStreamReader(response.getEntity().getContent(), "UTF-8")
    );
    System.out.println(jsonResponse);
  }
}
