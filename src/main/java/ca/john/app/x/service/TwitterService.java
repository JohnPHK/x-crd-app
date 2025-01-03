package ca.john.app.x.service;

import ca.john.app.x.dao.CrdDao;
import ca.john.app.x.model.TwitterData;
import org.springframework.beans.factory.annotation.Autowired;

public class TwitterService implements Service {

  private final CrdDao<TwitterData, String> dao;

  @Autowired
  public TwitterService(CrdDao<TwitterData, String> dao) {
    this.dao = dao;
  }

  @Override
  public TwitterData postTweet(String text) throws IllegalArgumentException {
    return dao.create(text);
  }

  @Override
  public TwitterData showTweet(String id) {
    return null;
  }

  @Override
  public TwitterData deleteTweets(String id) {
    return null;
  }

}
