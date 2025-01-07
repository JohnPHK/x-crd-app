package ca.app.x.service;

import ca.app.x.dao.CrdDao;
import ca.app.x.model.TwitterData;
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
    return dao.deleteById(id);
  }

}
