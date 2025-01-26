package ca.app.x.service;

import static org.junit.Assert.*;

import ca.app.x.dao.TwitterDataDao;
import ca.app.x.dao.helper.HttpHelper;
import ca.app.x.dao.helper.TwitterHttpHelper;
import ca.app.x.model.TwitterData;
import org.junit.Test;

public class TwitterServiceIntTest {

  String consumerKey = System.getenv("consumerKey");
  String consumerSecret = System.getenv("consumerSecret");
  String accessToken = System.getenv("accessToken");
  String tokenSecret = System.getenv("tokenSecret");

  HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
  TwitterDataDao dao = new TwitterDataDao(httpHelper);
  Service service = new TwitterService(dao);

  @Test
  public void testCreateFindAndDelete() {
    TwitterData twitterDataPost = service.postTweet("Testing Service!");

    assertNotNull(twitterDataPost);
    assertNotNull(twitterDataPost.getData());
    assertNotNull(twitterDataPost.getData().getId());
    assertNotNull(twitterDataPost.getData().getText());

    String generatedId = twitterDataPost.getData().getId();

    TwitterData twitterDataFound = service.showTweet(generatedId);

    String foundTwitterId = twitterDataFound.getData().getId();

    assertNotNull(twitterDataFound);
    assertNotNull(twitterDataFound.getData());
    assertEquals(generatedId, foundTwitterId);
    assertEquals("Testing Service!", twitterDataFound.getData().getText());

    TwitterData twitterDataDelete = service.deleteTweets(foundTwitterId);

    String deleteId = twitterDataDelete.getData().getId();
    assertNotNull(twitterDataDelete);
    assertNotNull(twitterDataDelete.getData());
    assertTrue(twitterDataDelete.getData().isDeleted());

  }

}
