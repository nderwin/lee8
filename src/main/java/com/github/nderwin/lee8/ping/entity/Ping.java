package com.github.nderwin.lee8.ping.entity;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

public class Ping {

    @JsonbProperty
    private final String message;

    @JsonbCreator
    public Ping(
            @JsonbProperty
            final String message
    ) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
