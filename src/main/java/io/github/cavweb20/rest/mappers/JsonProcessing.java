package io.github.cavweb20.rest.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class JsonProcessing implements ExceptionMapper<JsonProcessingException>
{
    @Override
    public Response toResponse(JsonProcessingException e)
    {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(e.getLocalizedMessage())
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}
