package io.github.cavweb20.rest.mappers;

import com.fasterxml.jackson.core.JsonParseException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class JsonParse implements ExceptionMapper<JsonParseException>
{
    @Override
    public Response toResponse(JsonParseException e)
    {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(e.getLocalizedMessage())
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}
