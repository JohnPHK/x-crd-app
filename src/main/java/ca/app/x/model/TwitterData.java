package ca.app.x.model;

import java.util.LinkedHashMap;
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
    "data"
})
@Generated("jsonschema2pojo")
public class TwitterData {

  private int status;

  @JsonProperty("data")
  private Data data;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

  @JsonProperty("data")
  public Data getData() {
    return data;
  }

  @JsonProperty("data")
  public void setData(Data data) {
    this.data = data;
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonProperty("status")
  public int getStatus() {
    return status;
  }

  @JsonProperty("status")
  public void setStatus(int status) {
    this.status = status;
  }

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Status: " + getStatus() + "\n");
    sb.append("Data: \n");
    sb.append(getData() == null ? "" : getData().toString());
    return sb.toString();
  }
  }