package io.github.cavweb20.rest.mappers;

import java.io.IOException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author cavweb20
 */
@Provider
public class IOExceptionMapper implements ExceptionMapper<IOException>
{
    @Override
    public Response toResponse(IOException e)
    {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(e.getLocalizedMessage())
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}
