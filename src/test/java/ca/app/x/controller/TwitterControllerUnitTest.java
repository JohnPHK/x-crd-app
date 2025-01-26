package ca.app.x.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import ca.app.x.service.TwitterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerUnitTest {

  @Mock
  TwitterService service;

  @InjectMocks
  TwitterController controller;

  @Captor
  ArgumentCaptor<String> tweetCaptor;

  @Test
  public void testPostTweet() {
    String[] args = new String[]{"post", "Test Twitter Controller"};

    controller.postTweet(args);

    verify(service, times(1)).postTweet(tweetCaptor.capture());

    assertEquals(args[1], tweetCaptor.getValue());

    // Fail case #1 - only post
    args = new String[]{"post"};
    try {
      controller.postTweet(args);
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    // Fail case
    args = new String[]{"post", "Test Twitter Controller", "fail"};
    try {
      controller.postTweet(args);
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    // Fail case
    args = new String[]{"possst", "Test Twitter Controller"};
    try {
      controller.postTweet(args);
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }

  @Test
  public void testShowTweet() {
    String[] args = new String[]{"show", "1"};

    controller.showTweet(args);

    verify(service, times(1)).showTweet(tweetCaptor.capture());

    assertEquals(args[1], tweetCaptor.getValue());

    // Failure case 1: argument length 1
    args = new String[]{"show"};
    try {
      controller.showTweet(args);
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    // Failure case 2: argument length more than 2
    args = new String[]{"show", "1", "fail"};
    try {
      controller.showTweet(args);
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    // Failure case 3: wrong spell of show
    args = new String[]{"shwo", "1"};
    try {
      controller.showTweet(args);
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }

  @Test
  public void testDeleteTweets() {
    String[] args = new String[]{"delete", "1"};

    controller.deleteTweet(args);

    verify(service, times(1)).deleteTweets(tweetCaptor.capture());

    assertEquals(args[1], tweetCaptor.getValue());

    // Failure Case 1 : more than 2 arguments.
    args = new String[]{"delete", "1", "fail"};
    try {
      controller.deleteTweet(args);
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    // Failure Case 2: Only one argument.
    args = new String[]{"delete"};
    try {
      controller.deleteTweet(args);
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    // Failure case 3: Wrong delete spell.
    args = new String[]{"delet", "1"};
    try {
      controller.deleteTweet(args);
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }


}
