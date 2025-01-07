package ca.app.x.util;

public class TweetUtil {

  public static void validateTweetText(String tweetText) {
    // Valid tweet text length
    if (tweetText.length() > 140) {
      throw new IllegalArgumentException("Text length too large, limit to 140 char");
    }
  }

  public static void validateTweetId(String id) {
    try {
      Long.parseLong(id);
    } catch (Exception e) {
      throw new IllegalArgumentException(
          "Invalid tweet id, either nonnumerical included in the String or out of range");
    }
  }
}