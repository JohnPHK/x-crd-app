package ca.john.app.x.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "edit_history_tweet_ids",
    "id",
    "text"
})
@Generated("jsonschema2pojo")
public class Data {

  @JsonProperty("edit_history_tweet_ids")
  private List<String> editHistoryTweetIds;
  @JsonProperty("id")
  private String id;
  @JsonProperty("text")
  private String text;
  @JsonProperty("deleted")
  private boolean deleted;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

  @JsonProperty("edit_history_tweet_ids")
  public List<String> getEditHistoryTweetIds() {
    return editHistoryTweetIds;
  }

  @JsonProperty("edit_history_tweet_ids")
  public void setEditHistoryTweetIds(List<String> editHistoryTweetIds) {
    this.editHistoryTweetIds = editHistoryTweetIds;
  }

  @JsonProperty("id")
  public String getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(String id) {
    this.id = id;
  }

  @JsonProperty("text")
  public String getText() {
    return text;
  }

  @JsonProperty("text")
  public void setText(String text) {
    this.text = text;
  }

  @JsonProperty("deleted")
  public boolean isDeleted() {
    return deleted;
  }

  @JsonProperty("deleted")
  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("\tedit_history_tweet_ids = ");
    for (String id : getEditHistoryTweetIds()) {
      sb.append(id + ", ");
    }
    sb.append("\n");
    sb.append("\ttext: ");
    sb.append(getText());
    sb.append("\n");
    sb.append("\tid : ");
    sb.append(getId());
    sb.append("\n");
    sb.append("\tdeleted: ");
    sb.append(isDeleted());
    return sb.toString();
  }

}