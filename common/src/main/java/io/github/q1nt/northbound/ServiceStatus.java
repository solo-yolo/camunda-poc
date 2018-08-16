package io.github.q1nt.northbound;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum ServiceStatus {
    @JsonProperty("in-progress")
    IN_PROGRESS("in-progress"),
    @JsonProperty("created")
    CREATED("created"),
    @JsonProperty("failed")
    FAILED("failed");


    private final String value;

    @JsonCreator
    ServiceStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
