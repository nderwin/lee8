package com.github.nderwin.lee8;

import io.swagger.jaxrs.config.BeanConfig;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("resources")
public class JAXRSConfiguration extends Application {

    @Inject
    @Property("application.version")
    private String version;

    @PostConstruct
    public void init() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion(version);
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setTitle("Learning EE 8");
        beanConfig.setBasePath("/resources");
        beanConfig.setResourcePackage("com.github.nderwin.lee8");
        beanConfig.setPrettyPrint(true);
        beanConfig.setScan(true);
    }

}
