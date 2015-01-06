package io.github.cavweb20.rest;

import io.github.cavweb20.rest.resources.CustomerResource;
import io.github.cavweb20.rest.resources.FormParameters;
import io.github.cavweb20.rest.resources.HelloUser;
import io.github.cavweb20.rest.resources.HelloWorld;
import io.github.cavweb20.rest.resources.Home;
import io.github.cavweb20.rest.resources.RequestHeaders;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;

public class RESTApplication extends Application
{
    @Override
    public Set<Class<?>> getClasses()
    {
        Set<Class<?>> clazzes = new HashSet<>();
        return clazzes;
    }
    
    @Override
    public Set<Object> getSingletons()
    {
        Set<Object> singletons = new HashSet<>();
        singletons.add(new Home());
        singletons.add(new HelloWorld());
        singletons.add(new HelloUser());
        singletons.add(new RequestHeaders());
        singletons.add(new FormParameters());
        singletons.add(new CustomerResource());
        return singletons;
    }
}
