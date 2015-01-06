package io.github.cavweb20.rest.resources;

import io.github.cavweb20.rest.om.Customer;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.util.HttpHeaderNames;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerResourceTest
{

    private final Logger LOG = LoggerFactory.getLogger(CustomerResourceTest.class);
    private static final Client client = ClientBuilder.newClient();
    private static String custURL = null;

    public CustomerResourceTest()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

    @Test
    public void _1_createCustomer() throws Exception
    {
        String url = "http://localhost:8080/service/customers/";
        String cust = "{\"name\":\"John Doe\"}";
        Entity<String> ecust = Entity.entity(cust, MediaType.APPLICATION_JSON);
        WebTarget target = client.target(url);
        Response response = target
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(ecust);
        Customer entity = response.readEntity(Customer.class);
        custURL = response.getHeaderString(HttpHeaderNames.LOCATION);
        assertEquals(response.getStatus(), 201);
        assertEquals(entity.getName(), "John Doe");
        assertEquals(entity.getId().version(), 1);
        assertTrue(entity.getId().toString().length() > 0);
    }

    @Test
    public void _2_getCustomer() throws Exception
    {
        WebTarget target = client.target(custURL);
        Response response = target
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get();
        Customer entity = response.readEntity(Customer.class);
        assertEquals(response.getStatus(), 200);
        assertEquals(entity.getName(), "John Doe");
        assertEquals(entity.getId().version(), 1);
        assertTrue(entity.getId().toString().length() > 0);
    }

    @Test
    public void _3_putCustomer() throws Exception
    {
        String cust = "{\"name\":\"Jane Doe\"}";
        Entity<String> ecust = Entity.entity(cust, MediaType.APPLICATION_JSON);
        WebTarget target = client.target(custURL);
        Response response = target
                .request(MediaType.APPLICATION_JSON)
                .put(ecust);
        String entity = response.readEntity(String.class);
        assertEquals(response.getStatus(), 200);
        assertTrue(entity.length() == 0);
    }

    @Test
    public void _4_getCustomer() throws Exception
    {
        WebTarget target = client.target(custURL);
        Response response = target
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get();
        Customer entity = response.readEntity(Customer.class);
        assertEquals(response.getStatus(), 200);
        assertEquals(entity.getName(), "Jane Doe");
        assertEquals(entity.getId().version(), 1);
        assertTrue(entity.getId().toString().length() > 0);
    }

    @Test
    public void _5_deleteCustomer()
    {
        WebTarget target = client.target(custURL);
        Response response = target
                .request()
                .delete();
        String entity = response.readEntity(String.class);
        assertEquals(response.getStatus(), 204);
        assertTrue(entity == null);
    }

    @Test
    public void _6_getCustomer() throws Exception
    {
        WebTarget target = client.target(custURL);
        Response response = target
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get();
        String entity = response.readEntity(String.class);
        assertEquals(response.getStatus(), 404);
        assertTrue(entity.length() > 0);
    }
}
