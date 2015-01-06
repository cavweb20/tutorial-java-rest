package io.github.cavweb20.rest.resources;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/feedback")
public class FormParameters
{

    /**
     * Default text to be returned by get.
     * 
     * @param name
     * @param email
     * @param message
     * @return headers
     */
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response getFormParameters(@FormParam(value = "name") String name,
            @FormParam(value = "email") String email,
            @FormParam(value = "message") String message)
    {
        StringBuilder response = new StringBuilder();
        response.append("Your name: ").append(name).append("\n");
        response.append("Your email: ").append(email).append("\n");
        response.append("Your message: ").append(message).append("\n");

        return Response.ok(response.toString()).build();
    }

}
