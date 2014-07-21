/**
*  Drew Wilson
*/
package com.google.cloud.genomics.localrepo.dto;

import com.google.cloud.genomics.localrepo.DataTransferObject;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class SearchVariantsRequest extends DataTransferObject {

  private static final ReflectiveHashCodeAndEquals<SearchVariantsRequest> HASH_CODE_AND_EQUALS =
      ReflectiveHashCodeAndEquals.create(SearchVariantsRequest.class);

  @JsonCreator public static SearchVariantsRequest create(
      @JsonProperty("datasetIds") List<String> datasetIds,
      @JsonProperty("callsetIds") List<String> callsetIds,
      @JsonProperty("sequenceName") String sequenceName,
      @JsonProperty("sequenceStart") Long sequenceStart,
      @JsonProperty("sequenceEnd") Long sequenceEnd,
      @JsonProperty("pageToken") String pageToken) {
    return new SearchVariantsRequest(
        datasetIds,
        callsetIds,
        sequenceName,
        sequenceStart,
        sequenceEnd,
        pageToken);
  }

  private final List<String> datasetIds;
  private final String pageToken;
  private final List<String> callsetIds;
  private final Long sequenceEnd;
  private final String sequenceName;
  private final Long sequenceStart;

  private SearchVariantsRequest(
      List<String> datasetIds,
      List<String> callsetIds,
      String sequenceName,
      Long sequenceStart,
      Long sequenceEnd,
      String pageToken) {
    this.datasetIds = datasetIds;
    this.callsetIds = callsetIds;
    this.sequenceName = sequenceName;
    this.sequenceStart = sequenceStart;
    this.sequenceEnd = sequenceEnd;
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

  public List<String> getCallsetIds() {
    return callsetIds;
  }

  public Long getSequenceEnd() {
    return sequenceEnd;
  }

  public String getSequenceName() {
    return sequenceName;
  }

  public Long getSequenceStart() {
    return sequenceStart;
  }

  @Override public int hashCode() {
    return HASH_CODE_AND_EQUALS.hashCode(this);
  }
}
