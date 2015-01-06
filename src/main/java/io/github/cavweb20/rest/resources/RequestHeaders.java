package io.github.cavweb20.rest.resources;

import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/headers")
public class RequestHeaders
{

    @GET
    @HEAD
    @OPTIONS
    @Produces(MediaType.TEXT_PLAIN)
    public Response getHeadersText(@Context HttpHeaders headers,
            @Context ServletContext context,
            @Context HttpServletRequest request)
    {
        Response.ResponseBuilder builder = Response
                .status(Response.Status.OK)
                .type(MediaType.TEXT_PLAIN);
        StringBuilder entity = new StringBuilder();
        Set<String> headersSet = headers.getRequestHeaders().keySet();
        switch(request.getMethod())
        {
            case "HEAD":
                break;
            case "OPTIONS":
                builder.allow("GET","HEAD", "OPTIONS");
                break;
            case "GET":
                entity.append("Request method: ").append(request.getMethod()).append("\n");
                entity.append("Client IP address: ").append(request.getRemoteAddr()).append("\n");
                entity.append("Server name: ").append(context.getServerInfo()).append("\n");
                entity.append("Servlet name: ").append(context.getServletContextName()).append("\n");

                entity.append("\nHTTP Request Headers:\n");
                for (String header : headersSet)
                {
                    entity.append(header).append(": ").append(headers.getRequestHeader(header)).append("\n");
                }
                break;
        }
        return builder.entity(entity).build();
    }
}
