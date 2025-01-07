package ca.app.x;

import ca.app.x.controller.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TwitterCLIApp {

  private final Controller controller;

  @Autowired
  public TwitterCLIApp(Controller controller) {
    this.controller = controller;
  }

  public static final String USAGE = "USAGE: TwitterCLIApp post|show|delete [options]";

  public void run(String[] args) {
    switch (args[0].toLowerCase()) {
      case "post":
        System.out.println(controller.postTweet(args));
        break;
      case "show" :
        System.out.println(controller.showTweet(args));
        break;
      case "delete":
        System.out.println(controller.deleteTweet(args));
        break;
      default:
        throw new IllegalArgumentException(USAGE);
    }
  }
}
