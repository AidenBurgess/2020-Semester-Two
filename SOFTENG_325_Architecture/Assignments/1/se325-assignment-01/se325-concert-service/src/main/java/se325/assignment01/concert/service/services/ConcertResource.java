package se325.assignment01.concert.service.services;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/concerts")
public class ConcertResource {

    @GET
    @Path("summaries")
    public Response getSummary() {
        return Response.ok().build();
    }


}
