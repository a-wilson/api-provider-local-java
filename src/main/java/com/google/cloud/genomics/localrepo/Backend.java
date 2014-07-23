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
package com.google.cloud.genomics.localrepo;

import com.google.cloud.genomics.localrepo.dto.Callset;
import com.google.cloud.genomics.localrepo.dto.Dataset;
import com.google.cloud.genomics.localrepo.dto.Readset;
import com.google.cloud.genomics.localrepo.dto.SearchReadsRequest;
import com.google.cloud.genomics.localrepo.dto.SearchReadsResponse;
import com.google.cloud.genomics.localrepo.dto.SearchVariantsRequest;
import com.google.cloud.genomics.localrepo.dto.SearchVariantsResponse;
import com.google.cloud.genomics.localrepo.util.Predicates;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Backend {

  public static Backend create(Collection<DatasetDirectory> datasets,
      int pageSize) {
    return new Backend(datasets.stream().collect(
        Collectors.toMap(((Function<Dataset, String>) Dataset::getId)
            .compose(DatasetDirectory::getDataset), Function.identity())),
        union(datasets.stream().map(DatasetDirectory::getReadsets)), pageSize);
  }

  private static <X, Y> Map<X, Y> union(Stream<Map<X, Y>> maps) {
    Map<X, Y> union = new HashMap<>();
    for (
        Iterator<Map<X, Y>> iterator = maps.iterator();
        iterator.hasNext();
        union.putAll(iterator.next()));
    return union;
  }

  private final Map<String, DatasetDirectory> datasets;
  private final Map<String, BamFilesReadset> readsets;
  private final QueryEngine queryEngine;

  private Backend(
      final Map<String, DatasetDirectory> datasets,
      Map<String, BamFilesReadset> readsets,
      int pageSize) {
    this.datasets = datasets;
    this.readsets = readsets;
    this.queryEngine = QueryEngine.create(datasets, readsets, pageSize);
  }

  public Optional<Dataset> getDataset(String datasetId) {
    return Optional.ofNullable(datasets.get(datasetId)).map(DatasetDirectory::getDataset);
  }

  public Optional<Readset> getReadset(String readsetId) {
    return Optional.ofNullable(readsets.get(readsetId)).map(BamFilesReadset::getReadset);
  }

  public Stream<Dataset> listDatasets() {
    return datasets.values().stream().map(DatasetDirectory::getDataset);
  }

  public SearchReadsResponse searchReads(SearchReadsRequest request) {
    return queryEngine.searchReads(request);
  }

  public Stream<Readset> searchReadsets(Collection<String> datasetIds) {
    return readsets.values()
        .stream()
        .filter(datasetIds.isEmpty()
            ? (readset) -> true
            : Predicates.compose(
                datasetId -> datasetIds.contains(datasetId),
                BamFilesReadset::getDatasetId))
        .map(BamFilesReadset::getReadset);
  }

public Optional<Callset> getCallset(String callsetId) {
	// TODO Auto-generated method stub
	return null;
}

public Stream<Callset> searchCallsets(List<String> datasetIds) {
	// TODO Auto-generated method stub
	return null;
}

public SearchVariantsResponse searchVariants(SearchVariantsRequest request) {
	// TODO Auto-generated method stub
	return null;
}

  /* In progress
  public SearchVariantsResponse searchVariants(SearchVariantsRequest request) {
    return queryEngine.searchVariants(request);
  }

  public Optional<Callset> getCallset(String callsetId) {
    return Optional.ofNullable(callsets.get(callsetId)).map(BamFilesReadset::getReadset);
  }

  public Stream<Callset> searchCallsets(Collection<String> datasetIds) {
    return callsets.values()
        .stream()
        .filter(datasetIds.isEmpty()
            ? (callset) -> true
            : Predicates.compose(
                datasetId -> datasetIds.contains(datasetId),
                BamFilesReadset::getDatasetId))
        .map(BamFilesReadset::getReadset);
  }*/
}
