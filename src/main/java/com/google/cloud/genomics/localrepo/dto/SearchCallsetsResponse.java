/**
*  Drew Wilson
*/
package com.google.cloud.genomics.localrepo.dto;

import com.google.cloud.genomics.localrepo.DataTransferObject;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class SearchCallsetsResponse extends DataTransferObject {

  private static final ReflectiveHashCodeAndEquals<SearchCallsetsResponse> HASH_CODE_AND_EQUALS =
      ReflectiveHashCodeAndEquals.create(SearchCallsetsResponse.class);

  @JsonCreator public static SearchCallsetsResponse create(
      @JsonProperty("callsets") List<Callset> callsets,
      @JsonProperty("nextPageToken") String nextPageToken) {
    return new SearchCallsetsResponse(callsets, nextPageToken);
  }

  private final String nextPageToken;
  private final List<Callset> callsets;

  private SearchCallsetsResponse(
      List<Callset> callsets,
      String nextPageToken) {
    this.callsets = callsets;
    this.nextPageToken = nextPageToken;
  }

  @Override public boolean equals(Object obj) {
    return HASH_CODE_AND_EQUALS.equals(this, obj);
  }

  public String getNextPageToken() {
    return nextPageToken;
  }

  public List<Callset> getCallsets() {
    return callsets;
  }

  @Override public int hashCode() {
    return HASH_CODE_AND_EQUALS.hashCode(this);
  }
}
