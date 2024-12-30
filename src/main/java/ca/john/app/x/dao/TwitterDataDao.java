package ca.john.app.x.dao;


import ca.john.app.x.model.TwitterData;

public class TwitterDataDao implements CrdDao<TwitterData, String>{

  // URI
  private static final String API_URI = "https://api.x.com/2/tweets";

  // URI symbols
  private static final String QUERY_SYM = "?";
  private static final String AMPERSAND = "&";
  private static final String EQUAL = "=";

  // Response code
  private static final int HTTP_OK = 200;

  @Override
  public TwitterData create(TwitterData entity) {
    return null;
  }

  @Override
  public TwitterData findById(String s) {
    return null;
  }

  @Override
  public TwitterData deleteById(String s) {
    return null;
  }
}
