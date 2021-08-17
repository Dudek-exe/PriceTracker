package com.mypricetracker.pricetracker.cucumber.steps.it;

import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@CucumberContextConfiguration
@ActiveProfiles(profiles = "test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringIntegrationConfiguration {

    @Before
    public void setUp() {
        log.info("----------- TEST CONTEXT SETUP -----------");
    }


}
