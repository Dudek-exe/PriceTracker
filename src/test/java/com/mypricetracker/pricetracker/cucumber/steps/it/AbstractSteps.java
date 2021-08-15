package com.mypricetracker.pricetracker.cucumber.steps.it;

import com.mypricetracker.pricetracker.cucumber.config.TestContextHolder;
import org.springframework.boot.web.server.LocalServerPort;

import static com.mypricetracker.pricetracker.cucumber.config.TestContextHolder.CONTEXT;

public abstract class AbstractSteps {

    @LocalServerPort
    private int localPort;

    private static final String BASE_URI = "http://localhost:";

    public String baseUrl() {
        return BASE_URI + localPort;
    }

    public TestContextHolder testContextHolder() {
        return CONTEXT;
    }


}
