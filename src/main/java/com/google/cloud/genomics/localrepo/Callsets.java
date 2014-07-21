/**
*  Drew Wilson
*/
package com.google.cloud.genomics.localrepo;

import com.google.cloud.genomics.localrepo.dto.SearchCallsetsResponse;
import com.google.cloud.genomics.localrepo.dto.SearchCallsetsRequest;

import java.util.stream.Collectors;

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

@Path("/callsets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Singleton
public class Callsets extends BaseResource {

  private final Backend backend;

  @Inject
  public Callsets(Backend backend) {
   	this.backend = backend;
  }

  @GET
  @Path("/{callsetId}")
  public Response get(@PathParam("callsetId") String callsetId) {
    return toResponse(backend.getCallset(callsetId));
  }

  @POST
  @Path("/search")
  public SearchCallsetsResponse search(SearchCallsetsRequest request) {
    return SearchCallsetsResponse.create(
        backend.searchCallsets(request.getDatasetIds()).collect(Collectors.toList()),
        null);
  }
}