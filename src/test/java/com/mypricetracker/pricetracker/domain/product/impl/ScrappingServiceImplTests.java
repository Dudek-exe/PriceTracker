package com.mypricetracker.pricetracker.domain.product.impl;

import com.mypricetracker.pricetracker.domain.product.ScrappingService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class ScrappingServiceImplTests {

    private static final String TEST_PRODUCT_URL = "https://www.x-kom.pl/p/648711-smartfon-telefon-apple-iphone-12-128gb-purple-5g.html";
    private static final String TEST_PRODUCT_NAME = "Apple iPhone 12 128GB Purple 5G";
    private static final BigDecimal TEST_PRODUCT_BORDER_PRICE = new BigDecimal("4449.00");

    @LocalServerPort
    private int localPort;

    @Before
    public void setup() {
        baseURI = "http://localhost";
        port = localPort;
    }

    @Autowired
    ScrappingService scrappingService;

    @Test
    public void POSTwithAllParamsShouldReturn200() throws JSONException {

        JSONObject jsonObj = new JSONObject()
                .put("url", TEST_PRODUCT_URL)
                .put("borderPrice", TEST_PRODUCT_BORDER_PRICE);

        given()
                .contentType("application/json")
                .body(jsonObj.toString())
                .when()
                .post("/scrap")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void GETwithAllParamsShouldReturn200() {

        given()
                .contentType("application/json")
                .param("productName", TEST_PRODUCT_NAME)
                .when()
                .get("/scrap")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void GETShouldReturnJSONArray() {

        given()
                .contentType("application/json")
                .param("productName", TEST_PRODUCT_NAME)
                .when()
                .get("/scrap")
                .then()
                .assertThat()
                .body("responseProductList", notNullValue())
                .body("responseProductList[0].productName", equalTo("Apple iPhone 12 128GB Purple 5G"))
                .body("responseProductList[0].priceDate", equalTo("2021-07-24T12:44:45.484797+02:00"))
                .body("responseProductList[0].borderPrice", equalTo(null))
                .and()
                .body("responseProductList[1].productName", equalTo("Apple iPhone 12 128GB Purple 5G"))
                .body("responseProductList[1].priceDate", equalTo("2021-07-24T12:45:05.36375+02:00"))
                .body("responseProductList[1].borderPrice", equalTo(null));
    }


}