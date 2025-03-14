package ca.app.x.spring;

import ca.app.x.TwitterCLIApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ca.app.x")
public class TwitterCLISpringBoot implements CommandLineRunner {
  private TwitterCLIApp app;

  @Autowired
  public TwitterCLISpringBoot(TwitterCLIApp app) {
    this.app = app;
  }

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(TwitterCLISpringBoot.class);

    //Turn off web
    app.setWebApplicationType(WebApplicationType.NONE);
    app.run(args);
  }

  @Override
  public void run(String... args) throws Exception {
    app.run(args);
  }

}
