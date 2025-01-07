package ca.app.x.service;

import ca.app.x.model.TwitterData;

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

  /**
   * Search a tweet by ID
   *
   * @param id     tweet id
   * @return Tweet object which is returned by the Twitter API
   * @throws IllegalArgumentException if id or fields param is invalid
   */
  TwitterData showTweet(String id);

  /**
   * Delete Tweet by ID.
   *
   * @param id tweet IDs which will be deleted
   * @return Deleted Tweet
   * @throws IllegalArgumentException if one of the IDs is invalid.
   */
  TwitterData deleteTweets(String id);

}
