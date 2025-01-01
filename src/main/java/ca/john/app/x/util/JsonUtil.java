package ca.john.app.x.util;

import ca.john.app.x.model.TwitterData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.IOException;

public class JsonUtil {
  public static <T> T toObjectFromJson(String JsonStr, Class<T> clazz) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(JsonStr, clazz);
  }
  public static String toPrettyJson(TwitterData twitterData) throws JsonProcessingException {
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    return ow.writeValueAsString(twitterData);
  }
}
