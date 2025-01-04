package ca.john.app.x.dao.helper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

@Component
public class TwitterHttpHelper implements HttpHelper {

  /**
   * Dependencies are specified as private member variables
   */
  private OAuthConsumer consumer;
  private HttpClient httpClient;

  public TwitterHttpHelper(String consumerKey, String consumerSecret, String accessToken,
      String tokenSecret) {
    consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
    consumer.setTokenWithSecret(accessToken, tokenSecret);

    /**
     * Default = single connection, Discuss source code if time permit
     */
    httpClient = HttpClientBuilder.create().build();
  }

  /**
   * Default constructor (not used for now)
   */
  public TwitterHttpHelper() {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
    consumer.setTokenWithSecret(accessToken, tokenSecret);

    httpClient = HttpClientBuilder.create().build();
  }

  @Override
  public HttpResponse httpGet(URI uri) {
    HttpGet httpGet = new HttpGet(uri);

    try {
      consumer.sign(httpGet);
      return httpClient.execute(httpGet);
    } catch (OAuthException e) {
      e.printStackTrace();
      throw new RuntimeException("Authorization with O-Auth failed", e);
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("Issue with sending request", e);
    }
  }

  @Override
  public HttpResponse httpPost(URI uri, String jsonBody) {
    HttpPost httpPost = new HttpPost(uri);

    StringEntity entity;
    try {
      entity = new StringEntity(jsonBody);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
      throw new RuntimeException("Unsupported Encoding Exception on the JSON BODY");
    }

    httpPost.setEntity(entity);
    httpPost.setHeader("Accept", "application/json");
    httpPost.setHeader("Content-type", "application/json");

    try {
      consumer.sign(httpPost);
      return httpClient.execute(httpPost);
    } catch (OAuthException e) {
      e.printStackTrace();
      throw new RuntimeException("Authorization with O-Auth failed", e);
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("Issue with sending request", e);
    }
  }


  public static void main(String[] args) throws URISyntaxException, OAuthException, IOException {
//    String consumerKey = System.getenv("consumerKey");
//    String consumerSecret = System.getenv("consumerSecret");
//    String accessToken = System.getenv("accessToken");
//    String tokenSecret = System.getenv("tokenSecret");

    String consumerKey = "qNfCpBwpHUEAIgvYN4dkNRmum";
    String consumerSecret = "wZ2qA1yvAjBknbr4l5yrpKn3uO68HVfjPp7UY8Ut53dtLS1nBu";
    String accessToken = "4896442139-D4h6qKxKCw9nrImFrsa7TqluMQ5gFXCqdupqaYw";
    String tokenSecret = "lCNmVaFcFQiDieQ2kzi9B2k3IYjA6jL97LeHDqzvZyJPX";

    HttpHelper httpHelper =
        new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);

    HttpResponse response;

    // Test Post Method
    System.out.println("========== Test Post Method ==========");
    String json = "{\"text\": \"Happy New year 2025 Alex\"}";

    response = httpHelper.httpPost(new URI("https://api.x.com/2/tweets"), json);
    System.out.println(EntityUtils.toString(response.getEntity()));

    // Test Get Method
    System.out.println("========== Test Get Method ==========");
    response = httpHelper.httpGet(new URI("https://api.x.com/2/tweets/1872403048800346384"));
    System.out.println(EntityUtils.toString(response.getEntity()));

  }

}
