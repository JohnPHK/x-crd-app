package ca.john.app.x.controller;

import ca.john.app.x.model.TwitterData;
import java.util.Collections;
import java.util.List;

public class TwitterController implements Controller{

  @Override
  public TwitterData postTweet(String[] args) {
    return null;
  }

  @Override
  public TwitterData showTweet(String[] args) {
    return null;
  }

  @Override
  public List<TwitterData> deleteTweet(String[] args) {
    return Collections.emptyList();
  }
}
