package io.github.cavweb20.rest.resources;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cavweb20.rest.om.Customer;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.spi.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/customers{var:/?}")
public class CustomerResource
{
    private final Logger LOG = LoggerFactory.getLogger(CustomerResource.class);
    private final ObjectMapper mapper = new ObjectMapper();
    private final Map<String, Customer> customerDB = new ConcurrentHashMap<>();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCustomer(InputStream is,
            @Context HttpRequest request) throws JsonParseException, IOException, URISyntaxException
    {
        Customer customer;
        StringBuilder uri = new StringBuilder(request.getUri().getRequestUri().toString());
        if(!uri.toString().endsWith("/"))
        {
            uri.append("/");
        }
        try
        {
            customer = readCustomer(is);
            uri.append(customer.getId().toString());
            customerDB.put(customer.getId().toString(), customer);
        }
        catch (JsonParseException e)
        {
            LOG.error(e.getLocalizedMessage());
            throw e;
        }
        catch (IOException e)
        {
            LOG.error(e.getLocalizedMessage());
            throw e;
        }
        return Response
                .status(Response.Status.CREATED)
                .type(MediaType.APPLICATION_JSON)
                .location(new URI(uri.toString()))
                .entity(mapper.writeValueAsString(customer)).build();
    }

    @Path("/{uuid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomer(@PathParam("uuid") String uuid,
            @Context HttpRequest request) throws JsonProcessingException
    {
        if (!customerDB.containsKey(uuid))
        {
            throw new NotFoundException("Resource " + request.getUri().getRequestUri().toString()
                + " not found");
        }
        Customer customer = customerDB.get(uuid);
        String json;
        try
        {
            json = mapper.writeValueAsString(customer);
        }
        catch (JsonProcessingException e)
        {
            LOG.error(e.getLocalizedMessage());
            throw e;
        }
        return Response
                .status(Response.Status.OK)
                .type(MediaType.APPLICATION_JSON)
                .entity(json).build();
    }
    
    @Path("/{uuid}")
    @DELETE
    public Response deleteCustomer(@PathParam("uuid") String uuid, @Context HttpRequest request)
    {
        if (!customerDB.containsKey(uuid))
        {
            throw new NotFoundException("Resource " + request.getUri().getRequestUri().toString()
                + " not found");
        }
        customerDB.remove(uuid);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @Path("/{uuid}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCustomer(InputStream is, @PathParam("uuid") String uuid,
            @Context HttpRequest request) throws JsonParseException, IOException
    {
        if (!customerDB.containsKey(uuid))
        {
            throw new NotFoundException("Resource " + request.getUri().getRequestUri().toString()
                + " not found");
        }
        Customer customer;
        try
        {
            customer = readCustomer(is);
            customer.setId(UUID.fromString(uuid));
            customerDB.put(uuid, customer);
        }
        catch (JsonParseException e)
        {
            LOG.error(e.getLocalizedMessage());
            throw e;
        }
        catch (IOException e)
        {
            LOG.error(e.getLocalizedMessage());
            throw e;
        }
        return Response.status(Response.Status.OK).build();
    }

    private Customer readCustomer(InputStream is) throws IOException
    {
        this.mapper.getDeserializationConfig()
                .with(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return this.mapper.readValue(is, Customer.class);
    }
}
