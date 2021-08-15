package com.mypricetracker.pricetracker.cucumber.steps.it;

import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;
import org.springframework.http.HttpStatus;

public class GlobalSteps extends AbstractSteps{

    @Then("HTTP Status is successful")
    public void httpStatusIs200() {
        final Response response = testContextHolder().getResponse();
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Then("HTTP Status is created")
    public void httpStatusIs201() {
        final Response response = testContextHolder().getResponse();
        Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
    }

}
