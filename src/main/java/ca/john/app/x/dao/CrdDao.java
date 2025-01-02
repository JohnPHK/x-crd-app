package ca.john.app.x.dao;

import org.apache.http.entity.StringEntity;

public interface CrdDao<T, ID> {
  /**
   * Create an entity(Tweet) to the underlying storage
   * @param text value of tweet to post on X
   * @return created entity
   */
  T create(String text);

  /**
   * Find an entity by its id
   * @param id tweet id
   * @return Tweet entity
   */
  T findById(ID id);

  /**
   * Delete an entity by its id
   * @param id of the tweet to be deleted
   * @return delete entity
   */
  T deleteById(ID id);
}