package com.github.nderwin.lee8.ping.boundary;

import com.github.nderwin.lee8.Property;
import io.swagger.annotations.Api;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

@Api
@Stateless
@LocalBean
@TransactionAttribute(REQUIRES_NEW)
@Path("ping")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
public class PingResource {

    @Inject
    @Property("application.version")
    private String version;

    @GET
    @Path("/")
    public Response get() {
        return Response
                .ok(new Ping(version))
                .build();
    }

    @XmlRootElement
    private class Ping {

        @XmlElement
        private final String message = "ping";

        @XmlElement
        private final String version;

        public Ping(final String version) {
            this.version = version;
        }

    }
}
