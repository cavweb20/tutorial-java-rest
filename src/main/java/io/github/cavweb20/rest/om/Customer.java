package io.github.cavweb20.rest.om;

import com.fasterxml.uuid.Generators;
import java.util.UUID;

/**
 *
 * @author cavweb20
 */
public class Customer
{
    private UUID id;
    private String name;

    public Customer()
    {
        this.id = Generators.timeBasedGenerator().generate();
    }

    public Customer(String name)
    {
        this.id = Generators.timeBasedGenerator().generate();
        this.name = name;
    }

    public UUID getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
