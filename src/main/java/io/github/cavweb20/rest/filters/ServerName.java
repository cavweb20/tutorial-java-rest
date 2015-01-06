package io.github.cavweb20.rest.filters;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class ServerName implements ContainerResponseFilter
{

    @Override
    public void filter(ContainerRequestContext creqCtx, ContainerResponseContext cresCtx)
    {
        cresCtx.getHeaders().putSingle("Server", "Custom REST server");
    }
}
