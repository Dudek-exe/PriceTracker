package com.mypricetracker.pricetracker;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = { "pretty", "json:target/cucumber-reports/cucumber.json"},
        features = "src/test/resources/features",
        glue = {"com.mypricetracker.pricetracker.cucumber.steps.it",
                "com.mypricetracker.pricetracker.cucumber.config"})
public class CucumberIntegrationTest {

}
