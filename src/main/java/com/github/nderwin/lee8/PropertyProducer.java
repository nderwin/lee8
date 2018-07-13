package com.github.nderwin.lee8;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class PropertyProducer {

    private final Properties properties = new Properties();

    @PostConstruct
    public void init() {
        try (final InputStream is = PropertyProducer.class.getResourceAsStream("/application.properties")) {
            if (null == is) {
                throw new RuntimeException("Missing application.properties file.");
            }

            this.properties.load(is);
        } catch (final IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Property
    @Produces
    public String produceString(final InjectionPoint ip) {
        return this.properties.getProperty(getKey(ip));
    }

    @Property
    @Produces
    public int produceInt(final InjectionPoint ip) {
        return Integer.valueOf(this.properties.getProperty(getKey(ip)));
    }

    @Property
    @Produces
    public boolean produceBoolean(final InjectionPoint ip) {
        return Boolean.valueOf(this.properties.getProperty(getKey(ip)));
    }

    private String getKey(final InjectionPoint ip) {
        if (ip.getAnnotated().isAnnotationPresent(Property.class)
                && !ip.getAnnotated().getAnnotation(Property.class).value().isEmpty()) {

            return ip.getAnnotated().getAnnotation(Property.class).value();
        } else {
            return ip.getMember().getName();
        }
    }
}
