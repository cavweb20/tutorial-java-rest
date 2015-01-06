package io.github.cavweb20.rest.mappers;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BadRequest implements ExceptionMapper<BadRequestException>
{
    @Override
    public Response toResponse(BadRequestException e)
    {
        String msg = "Bad Request";
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(msg)
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}
