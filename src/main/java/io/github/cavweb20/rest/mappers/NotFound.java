package io.github.cavweb20.rest.mappers;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFound implements ExceptionMapper<NotFoundException>
{
    @Override
    public Response toResponse(NotFoundException e)
    {
        String msg = "Resource Not Found";
        return Response.status(Response.Status.NOT_FOUND)
                .entity(msg)
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}
