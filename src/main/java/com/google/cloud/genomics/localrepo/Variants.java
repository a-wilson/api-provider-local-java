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

import com.google.cloud.genomics.localrepo.dto.SearchVariantsRequest;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/variants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Singleton
public class Variants extends BaseResource {

  private final Backend backend;

  @Inject
  public Variants(Backend backend) {
  	this.backend = backend;
  }
  
  @GET
  @Path("/{callsetId}")
  public Response get(@PathParam("callsetId") String callsetId) {
    return toResponse(backend.getCallset(callsetId));
  }

  @POST
  @Path("/search")
  public Response search(final SearchVariantsRequest request) {
    String datasetId = request.getDatasetId();
    String contig = request.getContig();
    List<String> startPosition = request.getStartPosition();
    List<String> endPosition = request.getEndPosition();
    return datasetId.isEmpty() || contig.isEmpty() 
    		|| startPosition.isEmpty() || endPosition.isEmpty()
	      ? Response.ok(backend.searchVariants(request)).build()
	      : BAD_REQUEST;
  }
}
