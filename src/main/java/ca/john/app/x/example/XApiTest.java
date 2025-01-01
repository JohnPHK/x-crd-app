package ca.john.app.x.example;

import com.google.gdata.util.common.base.PercentEscaper;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class XApiTest {

  private static final String CONSUMER_KEY = System.getenv("consumerKey");
  private static final String CONSUMER_SECRET = System.getenv("consumerSecret");
  private static final String ACCESS_TOKEN = System.getenv("accessToken");
  private static final String TOKEN_SECRET = System.getenv("tokenSecret");

  public static void main(String[] args) throws Exception {

    // Setup oauth
    OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
    consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);

    // Create an HTTP GET?? request - This seems POST
    String text = "today is is a good day";
    PercentEscaper percentEscaper = new PercentEscaper("", false);
    HttpPost request = new HttpPost("https://api.X.com/2/tweets");

    StringEntity entity;
    try {
      entity = new StringEntity(text);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
      throw new RuntimeException("Unsupported Encoding Exception on the JSON BODY");
    }

    request.setEntity(entity);
    request.setHeader("Accept", "application/json");
    request.setHeader("Content-type", "application/json");


    // Sign the request (add headers)
    consumer.sign(request);

    System.out.println("Http Request Headers:");
    Arrays.stream(request.getAllHeaders()).forEach(System.out::println);

    // Send the request
    HttpClient httpClient = HttpClientBuilder.create().build();
    HttpResponse response = httpClient.execute(request);
    System.out.println(EntityUtils.toString(response.getEntity()));

  }
}
