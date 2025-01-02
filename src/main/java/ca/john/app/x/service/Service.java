package ca.john.app.x.service;

import ca.john.app.x.model.TwitterData;

public interface Service {
  /**
   * Validate and post a user input Tweet
   *
   * @param text tweet to be created
   * @return created tweet
   *
   * @throws IllegalArgumentException if text exceed max number of allowed characters or lat/long out of range
   */
  TwitterData postTweet(String text);

}
