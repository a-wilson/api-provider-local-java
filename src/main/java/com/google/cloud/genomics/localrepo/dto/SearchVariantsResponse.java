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

public class SearchVariantsResponse extends DataTransferObject {

  private static final ReflectiveHashCodeAndEquals<SearchVariantsResponse> HASH_CODE_AND_EQUALS =
      ReflectiveHashCodeAndEquals.create(SearchVariantsResponse.class);

  @JsonCreator public static SearchVariantsResponse create(
      @JsonProperty("variants") List<Variant> variants,
      @JsonProperty("nextPageToken") String nextPageToken) {
    return new SearchVariantsResponse(variants, nextPageToken);
  }

  private final String nextPageToken;
  private final List<Variant> variants;

  private SearchVariantsResponse(List<Variant> variants, String nextPageToken) {
    this.variants = variants;
    this.nextPageToken = nextPageToken;
  }

  @Override public boolean equals(Object obj) {
    return HASH_CODE_AND_EQUALS.equals(this, obj);
  }

  public String getNextPageToken() {
    return nextPageToken;
  }

  public List<Variant> getVariants() {
    return variants;
  }

  @Override public int hashCode() {
    return HASH_CODE_AND_EQUALS.hashCode(this);
  }
}
