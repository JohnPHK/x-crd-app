package ca.app.x.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import ca.app.x.dao.helper.HttpHelper;
import ca.app.x.model.TwitterData;
import ca.app.x.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterDataDaoUnitTest {

  @Mock
  HttpHelper mockHelper;

  @InjectMocks
  TwitterDataDao dao;

  @Test
  public void testCreate() throws Exception {
    // Mock httpPost
    when(mockHelper.httpPost(isNotNull(), isNotNull())).thenThrow(new RuntimeException("mock"));

    try {
      dao.create("test");
      fail();
    } catch (RuntimeException e) {
      assertTrue(true);
    }

    String tweetJsonStr = "{\n"
        + "    \"data\": {\n"
        + "        \"text\": \"Test Number 10\",\n"
        + "        \"edit_history_tweet_ids\": [\n"
        + "            \"1881889479733969404\"\n"
        + "        ],\n"
        + "        \"id\": \"1881889479733969404\"\n"
        + "    }\n"
        + "}";

    // Mock parseResponseBody
    when(mockHelper.httpPost(isNotNull(), isNotNull())).thenReturn(null);
    TwitterDataDao spyDao = Mockito.spy(dao);
    TwitterData expectedData = JsonUtil.toObjectFromJson(tweetJsonStr, TwitterData.class);
    doReturn(expectedData).when(spyDao).parseResponseBody(any(), anyInt());

    TwitterData actualData = spyDao.create("Test Number 10");
    assertNotNull(actualData);
    assertNotNull(actualData.getData());
    assertEquals(expectedData, actualData);

  }

  @Test
  public void testFindById() throws Exception {
    // Mock httpGet
    when(mockHelper.httpGet(isNotNull())).thenThrow(new RuntimeException("mock"));

    try {
      dao.findById("1");
      fail();
    } catch (RuntimeException e) {
      assertTrue(true);
    }

    String sampleResponse = "{\n"
        + "    \"data\": {\n"
        + "        \"edit_history_tweet_ids\": [\n"
        + "            \"1881889479733969404\"\n"
        + "        ],\n"
        + "        \"id\": \"1881889479733969404\",\n"
        + "        \"text\": \"Test Number 10\"\n"
        + "    }\n"
        + "}";

    // Mock parseResponseBody
    when(mockHelper.httpGet(isNotNull())).thenReturn(null);
    TwitterDataDao spyDao = Mockito.spy(dao);
    TwitterData expectedData = JsonUtil.toObjectFromJson(sampleResponse, TwitterData.class);
    doReturn(expectedData).when(spyDao).parseResponseBody(any(), anyInt());
    TwitterData actualData = spyDao.findById("1");
    assertNotNull(actualData);
    assertNotNull(actualData.getData());
    assertEquals(expectedData, actualData);

  }

  @Test
  public void testDeleteById() throws Exception {
    // Mock httpDelete
    when(mockHelper.httpDelete(isNotNull())).thenThrow(new RuntimeException("mock"));
    try {
      dao.deleteById("1");
      fail();
    } catch (RuntimeException e) {
      assertTrue(true);
    }

    String sampleResponse = "{\n"
        + "    \"data\": {\n"
        + "        \"deleted\": true\n"
        + "    }\n"
        + "}";

    when(mockHelper.httpDelete(isNotNull())).thenReturn(null);
    TwitterDataDao spyDao = Mockito.spy(dao);
    TwitterData expectedData = JsonUtil.toObjectFromJson(sampleResponse, TwitterData.class);

    //Mock parseResponseBody
    doReturn(expectedData).when(spyDao).parseResponseBody(any(), anyInt());
    TwitterData actualData = spyDao.deleteById("1");
    assertNotNull(actualData);
    assertNotNull(actualData.getData());
    assertEquals(expectedData, actualData);

  }

}
