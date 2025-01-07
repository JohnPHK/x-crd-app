package ca.app.x.example;


import ca.app.x.model.TwitterData;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class XApiTest {

  public static final String exampleStr = "{\n"
      + "\"data\": {\n"
      + "\"edit_history_tweet_ids\" : [\n"
      + "\"1872403048800346384\"\n"
      + " ],\n"
      + "\"text\": \"Hello\",\n"
      + "\"id\": \"1872403048800346384\"\n"
      + "}\n"
      + "}";

  public static <T> T toObjectFromJson(String json, Class clazz) throws IOException {
    ObjectMapper m = new ObjectMapper();
//    m.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return (T) m.readValue(json, clazz);
  }

  public static void main(String[] args) throws Exception {
    System.out.println(exampleStr);

    TwitterData twitterData = toObjectFromJson(exampleStr, TwitterData.class);

    System.out.println(twitterData);

  }
}
