/**
*  Drew Wilson
*/
package com.google.cloud.genomics.localrepo;

import com.google.cloud.genomics.localrepo.dto.SearchVariantsRequest;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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

  @POST
  @Path("/search")
  public Response search(final SearchVariantsRequest request) {
    List<String> datasetIds = request.getDatasetIds();
    List<String> callsetIds = request.getCallsetIds();
    return datasetIds.isEmpty() || callsetIds.isEmpty()
        ? Response.ok(backend.searchVariants(request)).build()
        : BAD_REQUEST;
  }
}
