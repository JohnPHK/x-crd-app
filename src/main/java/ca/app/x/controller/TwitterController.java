package ca.app.x.controller;

import static ca.app.x.util.TweetUtil.validateTweetId;
import static ca.app.x.util.TweetUtil.validateTweetText;

import ca.app.x.model.TwitterData;
import ca.app.x.service.Service;
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
    if (args.length != 2 || !args[0].equals("post")) {
      throw new IllegalArgumentException("USAGE: TwitterCLIApp post <tweet_text>");
    }
    String tweetToPost = args[1];
    validateTweetText(tweetToPost);
    return service.postTweet(tweetToPost);
  }

  @Override
  public TwitterData showTweet(String[] args) {
    if (args.length != 2 || !args[0].equals("show")) {
      throw new IllegalArgumentException("USAGE: TwitterCLIApp show <tweet_id>");
    }

    String id = args[1];
    validateTweetId(id);
    return service.showTweet(id);
  }

  @Override
  public TwitterData deleteTweet(String[] args) {
    if (args.length != 2 || !args[0].equals("delete")) {
      throw new IllegalArgumentException("USAGE: TwitterCLIApp delete <tweet_id>");
    }
    String id = args[1];
    validateTweetId(id);
    return service.deleteTweets(id);
  }
}
