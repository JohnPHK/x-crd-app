package ca.app.x.spring;

import ca.app.x.TwitterCLIApp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value="ca.app.x")
public class TwitterCLIComponentScan {
  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(
        TwitterCLIComponentScan.class);
    TwitterCLIApp app = context.getBean(TwitterCLIApp.class);
    app.run(args);
  }
}
