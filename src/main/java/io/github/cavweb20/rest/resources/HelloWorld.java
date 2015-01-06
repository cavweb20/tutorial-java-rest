package io.github.cavweb20.rest.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("/helloworld")
public class HelloWorld
{
    private static final String HELLO_WORLD = "Hello World!";

    @GET
    @HEAD
    @OPTIONS
    @Produces(MediaType.TEXT_PLAIN)
    public Response getText(@QueryParam("user") String user,
            @Context HttpServletRequest request)
    {
        String entity = "";
        ResponseBuilder builder = Response
                .status(Response.Status.OK)
                .type(MediaType.TEXT_PLAIN);
        switch(request.getMethod())
        {
            case "HEAD":
                break;
            case "OPTIONS":
                builder.allow("GET","HEAD", "OPTIONS");
                break;
            case "GET":
                if (user == null)
                {
                    entity = HELLO_WORLD;
                }
                else
                {
                    entity = user + ": " + HELLO_WORLD;
                }                
                break;
        }
        return builder.entity(entity).build();
    }
}
