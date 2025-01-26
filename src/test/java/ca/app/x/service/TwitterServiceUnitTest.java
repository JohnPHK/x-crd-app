package ca.app.x.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ca.app.x.dao.CrdDao;
import ca.app.x.model.TwitterData;
import ca.app.x.util.JsonUtil;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {

  @Mock
  CrdDao<TwitterData, String> dao;

  @InjectMocks
  TwitterService service;

  String sampleResponse = "{\n"
      + "    \"data\": {\n"
      + "        \"edit_history_tweet_ids\": [\n"
      + "            \"1881889479733969404\"\n"
      + "        ],\n"
      + "        \"id\": \"1881889479733969404\",\n"
      + "        \"text\": \"Test Twitter Service\"\n"
      + "    }\n"
      + "}";

  String sampleResponseForDelete = "{\n"
      + "    \"data\": {\n"
      + "        \"deleted\": true\n"
      + "    }\n"
      + "}";

  @Test
  public void testPostTweet() throws IOException {
    TwitterData expectedData = JsonUtil.toObjectFromJson(sampleResponse, TwitterData.class);

    when(dao.create(any())).thenReturn(expectedData);

    TwitterData actualData = service.postTweet("Test Twitter Service");

    assertNotNull(actualData);
    assertNotNull(actualData.getData());
    assertEquals(expectedData.getData().getText(), actualData.getData().getText());
    assertEquals(expectedData.getData().getId(), actualData.getData().getId());
  }

  @Test
  public void testShowTweet() throws IOException {
    TwitterData expectedData = JsonUtil.toObjectFromJson(sampleResponse, TwitterData.class);

    when(dao.findById(any())).thenReturn(expectedData);

    TwitterData actualData = service.showTweet("1");

    assertNotNull(actualData);
    assertNotNull(actualData.getData());
    assertEquals(expectedData.getData().getText(), actualData.getData().getText());
    assertEquals(expectedData.getData().getId(), actualData.getData().getId());
  }

  @Test
  public void testDeleteTweets() throws IOException {
    TwitterData expectedData = JsonUtil.toObjectFromJson(sampleResponseForDelete, TwitterData.class);

    when(dao.deleteById(any())).thenReturn(expectedData);

    TwitterData actualData = service.deleteTweets("1");

    assertNotNull(actualData);
    assertNotNull(actualData.getData());
    assertEquals(expectedData.getData().isDeleted(), actualData.getData().isDeleted());
  }

}
