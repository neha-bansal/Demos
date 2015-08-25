package org.glassfish.jersey.examples.jsonmoxy;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("test")
public class JsonResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TestBean createSimpleBean() {
        return new TestBean("a", 1, 1L);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public TestBean roundTrip(TestBean s) {
        return s;
    }
}
