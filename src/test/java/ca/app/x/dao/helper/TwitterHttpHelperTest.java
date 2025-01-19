package ca.app.x.dao.helper;

import com.google.gdata.util.common.base.PercentEscaper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import oauth.signpost.exception.OAuthException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

public class TwitterHttpHelperTest {

  String consumerKey = System.getenv("consumerKey");
  String consumerSecret = System.getenv("consumerSecret");
  String accessToken = System.getenv("accessToken");
  String tokenSecret = System.getenv("tokenSecret");
  HttpHelper httpHelper;

  @Before
  public void init() {
    httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken,
        tokenSecret);
  }

  @Test
  public void httpPost() throws URISyntaxException, IOException {
    String jsonStr = "{\"text\": \" " + "Test Test in Test" + " \"}";

    HttpResponse response = httpHelper.httpPost(
        new URI("https://api.x.com/2/tweets"), jsonStr);
    System.out.println(EntityUtils.toString(response.getEntity()));
  }

  @Test
  public void httpGet() throws URISyntaxException, IOException {
    HttpResponse response = httpHelper.httpGet(new URI(
        "https://api.x.com/2/tweets/1872455505223840251"));
    System.out.println(EntityUtils.toString(response.getEntity()));
  }

}
