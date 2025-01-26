package ca.app.x.dao;

import static org.junit.Assert.*;

import ca.app.x.dao.helper.HttpHelper;
import ca.app.x.dao.helper.TwitterHttpHelper;
import ca.app.x.model.TwitterData;
import org.junit.Test;

public class TwitterDataDaoIntTest {
  String consumerKey = System.getenv("consumerKey");
  String consumerSecret = System.getenv("consumerSecret");
  String accessToken = System.getenv("accessToken");
  String tokenSecret = System.getenv("tokenSecret");

  HttpHelper  httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
  TwitterDataDao dao = new TwitterDataDao(httpHelper);

  @Test
  public void createFindAndDelete() {
    TwitterData twitterDataCreate = dao.create("Testing Tweet!");

    assertNotNull(twitterDataCreate);

    String generatedId = twitterDataCreate.getData().getId();

    TwitterData twitterDataFound = dao.findById(generatedId); //ID from above
    String foundTwitterId = twitterDataFound.getData().getId();

    assertNotNull(twitterDataFound);
    assertEquals(generatedId, foundTwitterId);
    assertEquals("Testing Tweet!", twitterDataFound.getData().getText());

    TwitterData twitterDataDeleted = dao.deleteById(foundTwitterId); // ID from above
    boolean deleted = twitterDataDeleted.getData().isDeleted();
    assertNotNull(twitterDataDeleted);
    assertTrue(deleted);

  }
}
