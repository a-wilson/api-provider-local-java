/*
Copyright 2014 Google Inc. All rights reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
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
  		@JsonProperty("datasetId") String datasetId,
  		@JsonProperty("callsetIds") List<String> callsetIds,
  		@JsonProperty("sequenceName") String sequenceName,
  		@JsonProperty("contig") String contig,
  		@JsonProperty("startPosition") List<String> startPosition,
  		@JsonProperty("endPosition") List<String> endPosition) {
    return new SearchVariantsRequest(
    		datasetId,
    		callsetIds,
        sequenceName,
        contig,
        startPosition,
        endPosition);
  }

  private final String datasetId;
  private final List<String> callsetIds;
  private final String sequenceName;
  private final String contig;
  private final List<String> startPosition;
  private final List<String> endPosition;

  private SearchVariantsRequest(
      String datasetId,
      List<String> callsetIds,
      String sequenceName,
      String contig,
      List<String> startPosition,
      List<String> endPosition) {
    this.datasetId = datasetId;
    this.callsetIds = callsetIds;
    this.sequenceName = sequenceName;
    this.contig = contig;
    this.startPosition = startPosition;
    this.endPosition = endPosition;
  }

  @Override public boolean equals(Object obj) {
    return HASH_CODE_AND_EQUALS.equals(this, obj);
  }

  public String getDatasetId() {
	  return datasetId;
  }

	public List<String> getCallsetIds() {
	  return callsetIds;
  }

	public String getSequenceName() {
	  return sequenceName;
  }

	/**
	 * @return the contig
	 */
  public String getContig() {
	  return contig;
  }

	/**
	 * @return the startPosition
	 */
  public List<String> getStartPosition() {
	  return startPosition;
  }

	/**
	 * @return the endPosition
	 */
  public List<String> getEndPosition() {
	  return endPosition;
  }

	@Override public int hashCode() {
    return HASH_CODE_AND_EQUALS.hashCode(this);
  }
}
