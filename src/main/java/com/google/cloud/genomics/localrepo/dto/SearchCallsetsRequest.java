/**
*  Drew Wilson
*/
package com.google.cloud.genomics.localrepo.dto;

import com.google.cloud.genomics.localrepo.DataTransferObject;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class SearchCallsetsRequest extends DataTransferObject {

  private static final ReflectiveHashCodeAndEquals<SearchCallsetsRequest> HASH_CODE_AND_EQUALS =
      ReflectiveHashCodeAndEquals.create(SearchCallsetsRequest.class);

  @JsonCreator public static SearchCallsetsRequest create(
      @JsonProperty("datasetIds") List<String> datasetIds,
      @JsonProperty("pageToken") String pageToken) {
    return new SearchCallsetsRequest(datasetIds, pageToken);
  }

  private final List<String> datasetIds;
  private final String pageToken;

  private SearchCallsetsRequest(
      List<String> datasetIds,
      String pageToken) {
    this.datasetIds = datasetIds;
    this.pageToken = pageToken;
  }

  @Override public boolean equals(Object obj) {
    return HASH_CODE_AND_EQUALS.equals(this, obj);
  }

  public List<String> getDatasetIds() {
    return datasetIds;
  }

  public String getPageToken() {
    return pageToken;
  }

  @Override public int hashCode() {
    return HASH_CODE_AND_EQUALS.hashCode(this);
  }
}
