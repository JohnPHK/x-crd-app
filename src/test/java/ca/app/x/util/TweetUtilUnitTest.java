package ca.app.x.util;

import static ca.app.x.util.JsonUtil.toObjectFromJson;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import ca.app.x.model.TwitterData;
import java.io.IOException;
import org.junit.Test;

public class TweetUtilUnitTest {

  @Test
  public void jsonToObject() throws IOException {
    String jsonStr = "{\n"
        + "\"data\": {\n"
        + "\"edit_history_tweet_ids\" : [\n"
        + "\"1234\"\n"
        + " ],\n"
        + "\"text\": \"Test\",\n"
        + "\"id\": \"1234\"\n"
        + "}\n"
        + "}";

    TwitterData twitterData = toObjectFromJson(jsonStr, TwitterData.class);
    assertEquals("1234", twitterData.getData().getEditHistoryTweetIds().get(0));
    assertEquals("Test", twitterData.getData().getText());
    assertEquals("1234", twitterData.getData().getId());
  }

  @Test
  public void validateTweetText() {

    /* Fail Scenario */
    try {
      String text = new String(new char[141]).replace("\0", "A");
      TweetUtil.validateTweetText(text);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    /* Successful Scenario */
    try {
      TweetUtil.validateTweetText("Test");
      assertTrue(true);
    } catch (IllegalArgumentException e) {
      fail();
    }

  }

  @Test
  public void validateTweetId() {

    /* Fail Scenario */
    try {
      TweetUtil.validateTweetId("fail test");
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    /* Successful Scenario */
    try {
      TweetUtil.validateTweetId("1234");
      assertTrue(true);
    } catch (IllegalArgumentException e) {
      fail();
    }

  }
}
