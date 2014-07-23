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
package com.google.cloud.genomics.localrepo.vcf;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * The header record of a VCF file. All this is essentially is a list of sample IDs.
 */
public final class VCFHeader {

  /**
   * A builder class for {@code Header} objects.
   */
  public static final class Builder {

    private final ImmutableList.Builder<String> sampleIds;

    Builder(List<String> sampleIds) {
      this.sampleIds = ImmutableList.<String>builder().addAll(sampleIds);
    }

    /**
     * Add a new sample Id to the list of sample Ids in the header.
     */
    public Builder addSampleId(String sampleId) {
      this.sampleIds.add(sampleId);
      return this;
    }

    /**
     * Build the header.
     */
    public VCFHeader build() {
      return new VCFHeader(sampleIds.build());
    }
  }

  /**
   * Static factory method for the {@code Header.Builder} class.
   */
  public static Builder builder() {
    return new Builder(Collections.<String>emptyList());
  }

  private final List<String> sampleIds;

  VCFHeader(List<String> sampleIds) {
    this.sampleIds = sampleIds;
  }

  @Override
  public boolean equals(Object obj) {
    return this == obj
        || null != obj
        && VCFHeader.class == obj.getClass()
        && Objects.equals(sampleIds(), ((VCFHeader) obj).sampleIds());
  }

  /**
   * Return the list of sample Ids.
   */
  public List<String> sampleIds() {
    return sampleIds;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(sampleIds());
  }

  /**
   * Convert this {@code Header} into its {@code Header.Builder} representation.
   */
  public Builder toBuilder() {
    return new Builder(sampleIds());
  }

  @Override
  public String toString() {
    List<String> sampleIds = sampleIds();
    return String.format(
        "#CHROM\tPOS\tID\tREF\tALT\tQUAL\tFILTER\tINFO%s",
        sampleIds.isEmpty() ? "" : String.format("\tFORMAT\t%s", Joiner.on('\t').join(sampleIds)));
  }
}