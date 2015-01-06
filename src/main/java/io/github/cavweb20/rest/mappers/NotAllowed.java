package io.github.cavweb20.rest.mappers;

import javax.ws.rs.NotAllowedException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotAllowed implements ExceptionMapper<NotAllowedException>
{
    @Override
    public Response toResponse(NotAllowedException e)
    {
        String msg = "Method Not Allowed";
        return Response.status(Response.Status.METHOD_NOT_ALLOWED)
                .entity(msg)
                .type(MediaType.TEXT_PLAIN)
                .allow("GET", "HEAD", "OPTIONS")
                .build();
    }
}
