package io.github.cavweb20.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello/{user}")
public class HelloUser
{
    private static final String HELLO = "Hello ";

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getText(@PathParam("user") String user)
    {
        String entity;
        Response.ResponseBuilder builder = Response
                .status(Response.Status.OK)
                .type(MediaType.TEXT_PLAIN);
        if (user == null)
        {
            entity = HELLO + "!";
        }
        else
        {
            entity = HELLO + user + "!";
        }                
        return builder.entity(entity).build();
    }

    @OPTIONS
    @Produces(MediaType.TEXT_PLAIN)
    public Response optionsText(@PathParam("user") String user)
    {
        String entity = "Usage: /hello/{user}";
        return Response
                .status(Response.Status.OK)
                .type(MediaType.TEXT_PLAIN)
                .allow("GET","HEAD", "OPTIONS")
                .entity(entity).build();
    }

    @HEAD
    @Produces(MediaType.TEXT_PLAIN)
    public Response headText(@PathParam("user") String user)
    {
        return Response
                .status(Response.Status.OK)
                .type(MediaType.TEXT_PLAIN)
                .entity("").build();
    }
}
