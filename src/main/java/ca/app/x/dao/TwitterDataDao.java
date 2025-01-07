package ca.app.x.dao;


import ca.app.x.dao.helper.HttpHelper;
import ca.app.x.dao.helper.TwitterHttpHelper;
import ca.app.x.model.TwitterData;
import ca.app.x.util.JsonUtil;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TwitterDataDao implements CrdDao<TwitterData, String>{

  // URI
  private static final String API_URI = "https://api.x.com/2/tweets";

  // URI symbols
  private static final String QUERY_SYM = "?";
  private static final String AMPERSAND = "&";
  private static final String EQUAL = "=";

  // Response code
  private static final int HTTP_OK = 200;
  private static final int HTTP_ACCEPTED = 201;

  private final HttpHelper httpHelper;

  private final Logger logger = LoggerFactory.getLogger(TwitterDataDao.class);

  @Autowired
  public TwitterDataDao(HttpHelper httpHelper) {
    this.httpHelper = httpHelper;
  }

  @Override
  public TwitterData create(String text) {
    URI uri;
    try {
      uri = new URI(API_URI);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("Invalid tweet input", e);
    }

    HttpResponse response = httpHelper.httpPost(uri, createJsonBody(text));
    return parseResponseBody(response, HTTP_ACCEPTED);
  }

  @Override
  public TwitterData findById(String s) {
    URI uri;
    try {
      uri = new URI(API_URI + "/" + s);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("Invalid tweet input", e);
    }

    HttpResponse response = httpHelper.httpGet(uri);
    return parseResponseBody(response, HTTP_OK);
  }

  @Override
  public TwitterData deleteById(String s) {
    URI uri;
    try {
      uri = new URI(API_URI + "/" + s);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("Invalid ID to delete", e);
    }
    HttpResponse response = httpHelper.httpDelete(uri);
    return parseResponseBody(response, HTTP_OK);
  }

  private String createJsonBody(String text) {
    return "{\"text\": \" " + text + " \"}";
  }

  public TwitterData parseResponseBody(HttpResponse response, int expectedStatusCode) {

    // Check response status
    int status = response.getStatusLine().getStatusCode();
    if (status != expectedStatusCode) {
      return unexpectedStatusResponse(response, status);
    }

    if (response.getEntity() == null) {
      throw new RuntimeException("Empty response body");
    }

    //Convert Response Entity to str
    TwitterData twitterData;
    String jsonStr;
    try {
      jsonStr = EntityUtils.toString(response.getEntity());
      logger.debug("Response in JSON String: {}", jsonStr);
    } catch (IOException e) {
      throw new RuntimeException("Failed to convert entity to String", e);
    }

    // Deserialize JSON string to Tweet object
    try {
      twitterData = JsonUtil.toObjectFromJson(jsonStr, TwitterData.class);
      twitterData.setStatus(status);
      logger.debug(twitterData.toString());
    } catch (IOException e) {
      throw new RuntimeException("Unable to convert JSON str to Object", e);
    }

    return twitterData;
  }

  private TwitterData unexpectedStatusResponse(HttpResponse response, int status) {
    System.out.println("Request failed - details below: ");
    System.out.println("\tStatus: "+ status);
    System.out.println("\tReason: " + response.getStatusLine().getReasonPhrase());
    TwitterData twitterData = new TwitterData();
    twitterData.setStatus(status);
    return twitterData;
  }

  public static void main(String[] args) {
    String consumerKey = "qNfCpBwpHUEAIgvYN4dkNRmum";
    String consumerSecret = "wZ2qA1yvAjBknbr4l5yrpKn3uO68HVfjPp7UY8Ut53dtLS1nBu";
    String accessToken = "4896442139-D4h6qKxKCw9nrImFrsa7TqluMQ5gFXCqdupqaYw";
    String tokenSecret = "lCNmVaFcFQiDieQ2kzi9B2k3IYjA6jL97LeHDqzvZyJPX";

    HttpHelper httpHelper1 = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken,
        tokenSecret);
    CrdDao<TwitterData, String> dao = new TwitterDataDao(httpHelper1);

//    System.out.println("========= Testing Create Method ==========");
//    TwitterData twitterData_post = dao.create("Hopefully 2025 is a better year please!");
//    System.out.println(twitterData_post);
//
//    System.out.println("========= Testing FindById Method ==========");
//    TwitterData twitterData_get = dao.findById("1872403048800346384");
//    System.out.println(twitterData_get);

    System.out.println("========= Testing DeleteById Method ==========");
    TwitterData twitterData_delete = dao.deleteById("1874296946099175555");
    System.out.println(twitterData_delete);
  }

}
