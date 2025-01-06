package ca.john.app.x.controller;

import ca.john.app.x.model.TwitterData;
import ca.john.app.x.service.Service;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Controller
public class TwitterController implements Controller{

  private Service service;

  @Autowired
  public TwitterController(Service service) {
    this.service = service;
  }

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
