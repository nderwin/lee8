package com.github.nderwin.lee8.ping.boundary;

import com.github.nderwin.lee8.ping.entity.Ping;
import com.github.nderwin.lee8.Property;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

@Stateless
@LocalBean
@TransactionAttribute(REQUIRES_NEW)
@Path("ping")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
public class PingResource {

    @PersistenceContext
    EntityManager em;

    @Inject
    @Property("application.version")
    String version;

    @GET
    @Path("/")
    public Response get() {
        final Object result = em.createNativeQuery("SELECT now()").getSingleResult();

        return Response
                .ok(new Ping(version + "-" + result))
                .build();
    }

}
