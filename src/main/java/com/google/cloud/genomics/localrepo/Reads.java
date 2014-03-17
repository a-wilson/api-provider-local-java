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

import com.google.cloud.genomics.localrepo.dto.SearchReadsRequest;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/reads")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Singleton
public class Reads extends BaseResource {

  private final Backend backend;

  @Inject
  public Reads(Backend backend) {
    this.backend = backend;
  }

  @POST
  @Path("/search")
  public Response search(final SearchReadsRequest request) {
    List<String> datasetIds = request.getDatasetIds();
    List<String> readsetIds = request.getReadsetIds();
    return datasetIds.isEmpty() || readsetIds.isEmpty()
        ? Response.ok(backend.searchReads(request)).build()
        : BAD_REQUEST;
  }
}
