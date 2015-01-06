package io.github.cavweb20.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("{var:/?}")
public class Home
{
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response homePage()
    {
        String entity = "Service Home Page";
        return Response
                .status(Response.Status.OK)
                .type(MediaType.TEXT_PLAIN)
                .entity(entity).build();
    }
}
