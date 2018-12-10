package com.github.nderwin.lee8;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.Components;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

@ApplicationPath("resources")
@OpenAPIDefinition(
        info = @Info(
                title = "LEE8 API",
                version = "1"
        ),
        components = @Components()
)
public class JAXRSConfiguration extends Application {

}
