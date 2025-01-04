package ca.john.app.x.dao.helper;

import java.net.URI;
import org.apache.http.HttpResponse;

public interface HttpHelper {

  /**
   * Execute a HTTP Get call
   * @param uri
   * @return
   */
  HttpResponse httpGet(URI uri);

  /**
   * Execute a HTTP Post call
   * @param uri
   * @return
   */
  HttpResponse httpPost(URI uri, String jsonBody);

  /**
   * Execute a HTTP Delete call
   * @param uri
   * @return
   */
  HttpResponse httpDelete(URI uri);
}
