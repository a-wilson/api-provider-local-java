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
import java.util.Map;

public class Variant extends DataTransferObject {
	
	public static class Calls extends DataTransferObject {

	  private static final ReflectiveHashCodeAndEquals<Calls> HASH_CODE_AND_EQUALS =
	      ReflectiveHashCodeAndEquals.create(Calls.class);
	
	  @JsonCreator public static Calls create(
	      @JsonProperty("callsetId") String callsetId,
	      @JsonProperty("callsetName") String callsetName,
	      @JsonProperty("genotype") List<Integer> genotype,
	      @JsonProperty("phaseset") String phaseset,
	      @JsonProperty("genotypeLikelihood") List<Double> genotypeLikelihood,
	      @JsonProperty("info") Map<String, String> info) {
	    return new Calls(
	    		callsetId,
	    		callsetName,
	    		genotype,
	    		phaseset,
	    		genotypeLikelihood,
	    		info);
	  }
	
	  private final String callsetId;
	  private final String callsetName;
	  private final List<Integer> genotype;
	  private final String phaseset;
	  private final List<Double> genotypeLikelihood;
	  private final Map<String, String> info;
	
	  private Calls(
	      String callsetId,
	      String callsetName,
	      List<Integer> genotype,
	      String phaseset,
	      List<Double> genotypeLikelihood,
	      Map<String, String> info) {
	    this.callsetId = callsetId;
	    this.callsetName = callsetName;
	    this.genotype = genotype;
	    this.phaseset = phaseset;
	    this.genotypeLikelihood = genotypeLikelihood;
	    this.info = info;
	  }
	
	  @Override public boolean equals(Object obj) {
	    return HASH_CODE_AND_EQUALS.equals(this, obj);
	  }
	
	  public String getCallsetId() {
	    return callsetId;
	  }
	
	  public String getCallsetName() {
	    return callsetName;
	  }
	  
	  public List<Integer> getGenotype() {
		  return genotype;
	  }
	  
	  public String getPhaseset() {
		  return phaseset;
	  }
	  
	  public List<Double> getGenotypeLikelihood() {
		  return genotypeLikelihood;
	  }
	  
	  public Map<String, String> getInfo() {
		  return info;
	  }
	
	  @Override public int hashCode() {
	    return HASH_CODE_AND_EQUALS.hashCode(this);
	  }
	}

  private static final ReflectiveHashCodeAndEquals<Variant> HASH_CODE_AND_EQUALS =
      ReflectiveHashCodeAndEquals.create(Variant.class);

  @JsonCreator public static Variant create(
      @JsonProperty("id") String id,
      @JsonProperty("datasetId") String datasetId,
      @JsonProperty("names") List<String> names,
      @JsonProperty("created") long created,
      @JsonProperty("contig") String contig,
      @JsonProperty("position") long position,
      @JsonProperty("referenceBases") String referenceBases,
      @JsonProperty("alternateBases") List<String> alternateBases,
      @JsonProperty("info") Map<String, String> info,
      @JsonProperty("calls") List<Calls> calls) {
    return new Variant(
        id,
        datasetId,
        names,
        created,
        contig,
        position,
        referenceBases,
        alternateBases,
        info,
        calls);
  }

  private final String id;
  private final String datasetId;
  private final List<String> comments;
  private final long created;
  private final String contig;
  private final long position;
  private final String referenceBases;
  private final List<String> alternateBases;
  private final Map<String, String> info;
  private final List<Calls> calls;
  

  private Variant(
      String id,
      String datasetId,
      List<String> comments,
      long created,
      String contig,
      long position,
      String referenceBases,
      List<String> alternateBases,
      Map<String, String> info,
      List<Calls> calls) {
    this.id = id;
    this.datasetId = datasetId;
    this.comments = comments;
    this.created = created;
    this.contig = contig;
    this.position = position;
    this.referenceBases = referenceBases;
    this.alternateBases = alternateBases;
    this.info = info;
    this.calls = calls;
  }

  @Override public boolean equals(Object obj) {
    return HASH_CODE_AND_EQUALS.equals(this, obj);
  }

  public String getId() {
    return id;
  }
  
  public String getDatasetId() {
	  return datasetId;
  }
  
  public List<String> getComments() {
	  return comments;
  }
  
  public long getCreated() {
	  return created;
  }
  
  public String getContig() {
	  return contig;
  }
  
  public long getPosition() {
	  return position;
  }
  
  public String getReferenceBases() {
	  return referenceBases;
  }
  
  public List<String> getAlternateBases() {
	  return alternateBases;
  }
  
  public Map<String, String> getInfo() {
	  return info;
  }
  
  public List<Calls> getCalls() {
	  return calls;
  }

  @Override public int hashCode() {
    return HASH_CODE_AND_EQUALS.hashCode(this);
  }
}
