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

    StringEntity entity = null;
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

    String consumerKey = "4CjhcpPe1G4msie8yLtcZfdUR";
    String consumerSecret = "ZWSwqwAk0XxMSm6vqkVgRjl4TQx1yDAwhLLnkm3XAdO0jsz0Kf";
    String accessToken = "4896442139-6vhnV9EaJkehF6SuJ0IcTDINOhfsfhMh4N4ThFv";
    String tokenSecret = "INUw4y6tvtHJcR8B6Dw5vJ2D35Px63zMeR7cJ3YpF6eu8";

    HttpHelper httpHelper =
        new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);

    HttpResponse response;

    // Test Post Method
    System.out.println("========== Test Post Method ==========");
    String json = "{\"text\": \"Test from CRD App\"}";

    response = httpHelper.httpPost(new URI("https://api.X.com/2/tweets"), json);
    System.out.println(EntityUtils.toString(response.getEntity()));

    // Test Get Method
    System.out.println("========== Test Get Method ==========");
    response = httpHelper.httpGet(new URI("https://api.X.com/2/tweets/1872403048800346384"));
    System.out.println(EntityUtils.toString(response.getEntity()));

  }

}
