package ca.app.x.controller;

import static org.junit.Assert.*;

import ca.app.x.dao.TwitterDataDao;
import ca.app.x.dao.helper.HttpHelper;
import ca.app.x.dao.helper.TwitterHttpHelper;
import ca.app.x.model.TwitterData;
import ca.app.x.service.TwitterService;
import org.junit.Test;

public class TwitterControllerIntTest {
  String consumerKey = System.getenv("consumerKey");
  String consumerSecret = System.getenv("consumerSecret");
  String accessToken = System.getenv("accessToken");
  String tokenSecret = System.getenv("tokenSecret");

  HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
  TwitterDataDao dao = new TwitterDataDao(httpHelper);
  TwitterService service = new TwitterService(dao);
  Controller controller = new TwitterController(service);

  @Test
  public void testPostFindAndDelete() {
    String[] args = new String[]{"post", "Integration Test Controller"};

    TwitterData twitterDataPost = controller.postTweet(args);

    assertNotNull(twitterDataPost);
    assertNotNull(twitterDataPost.getData());
    assertNotNull(twitterDataPost.getData().getId());
    assertEquals("Integration Test Controller", twitterDataPost.getData().getText());

    args = new String[]{"show", twitterDataPost.getData().getId()};

    TwitterData twitterDataShow = controller.showTweet(args);

    assertNotNull(twitterDataShow);
    assertNotNull(twitterDataShow.getData());
    assertEquals(twitterDataPost.getData().getId(), twitterDataShow.getData().getId());
    assertEquals("Integration Test Controller", twitterDataShow.getData().getText());


    args = new String[]{"delete", twitterDataShow.getData().getId()};

    TwitterData twitterDataDelete = controller.deleteTweet(args);

    assertNotNull(twitterDataDelete);
    assertNotNull(twitterDataDelete.getData());
    assertTrue(twitterDataDelete.getData().isDeleted());

  }
}
