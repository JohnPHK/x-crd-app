package ca.john.app.x.service;

import ca.john.app.x.dao.CrdDao;
import ca.john.app.x.model.TwitterData;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
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
    return dao.findById(id);
  }

  @Override
  public TwitterData deleteTweets(String id) {
    return null;
  }

}
