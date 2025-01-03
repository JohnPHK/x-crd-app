package ca.john.app.x.controller;

import ca.john.app.x.model.TwitterData;
import java.util.List;

public interface Controller {
  /**
   * Parse user argument and post a tweet by calling service classes
   *
   * @param args
   * @return a posted tweet
   * @throws IllegalArgumentException if args are invalid
   */
  TwitterData postTweet(String[] args);

  /**
   * Parse user argument and search a tweet by calling service classes
   *
   * @param args
   * @return a tweet
   * @throws IllegalArgumentException if args are invalid
   */
  TwitterData showTweet(String[] args);

  /**
   * Parse user argument and delete tweets by calling service classes
   *
   * @param args
   * @return a list of deleted tweets
   * @throws IllegalArgumentException if args are invalid
   */
  List<TwitterData> deleteTweet(String[] args);
}
