package io.github.cavweb20.rest.mappers;

import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotAcceptable implements ExceptionMapper<NotAcceptableException>
{
    @Override
    public Response toResponse(NotAcceptableException e)
    {
        String msg = "Unsupported Media Type";
        return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE)
                .entity(msg)
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}
