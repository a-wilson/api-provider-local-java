/**
*  Drew Wilson
*/
package com.google.cloud.genomics.localrepo.dto;

import com.google.cloud.genomics.localrepo.DataTransferObject;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class SearchVariantsResponse extends DataTransferObject {

  private static final ReflectiveHashCodeAndEquals<SearchVariantsResponse> HASH_CODE_AND_EQUALS =
      ReflectiveHashCodeAndEquals.create(SearchVariantsResponse.class);

  @JsonCreator public static SearchVariantsResponse create(
      @JsonProperty("reads") List<Read> reads,
      @JsonProperty("nextPageToken") String nextPageToken) {
    return new SearchVariantsResponse(reads, nextPageToken);
  }

  private final String nextPageToken;
  private final List<Read> reads;

  private SearchVariantsResponse(List<Read> reads, String nextPageToken) {
    this.reads = reads;
    this.nextPageToken = nextPageToken;
  }

  @Override public boolean equals(Object obj) {
    return HASH_CODE_AND_EQUALS.equals(this, obj);
  }

  public String getNextPageToken() {
    return nextPageToken;
  }

  public List<Read> getReads() {
    return reads;
  }

  @Override public int hashCode() {
    return HASH_CODE_AND_EQUALS.hashCode(this);
  }
}
