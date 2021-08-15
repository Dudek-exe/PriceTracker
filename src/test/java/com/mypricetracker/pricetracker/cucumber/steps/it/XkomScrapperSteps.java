package com.mypricetracker.pricetracker.cucumber.steps.it;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class XkomScrapperSteps extends AbstractSteps {

    private static final String SCRAP_ENDPOINT = "/scrap";

    private static String translateNullSentinel(String value) {
        return (value.trim().equals("null")) ? null : value.trim();
    }

    @When("User prepares and executes request as below:")
    public void userPreparesAndExecutesRequestAsBelow(DataTable dataTable) throws JSONException {
        final String executionUrl = baseUrl() + SCRAP_ENDPOINT;
        RestAssured.defaultParser = Parser.JSON;
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        JSONObject requestBody = new JSONObject();
        for (Map<String, String> columns : rows) {
            requestBody.put("url", translateNullSentinel(columns.get("url")));
            requestBody.put("borderPrice", translateNullSentinel(columns.get("borderPrice")));
        }

        testContextHolder().setPayload(requestBody.toString());

        final Response response = (Response) given()
                .body(testContextHolder().getPayload())
                .contentType(ContentType.JSON)
                .log()
                .all()
                .when()
                .post(executionUrl)
                .then().body("productName", Matchers.is("Apple iPhone 12 128GB Purple 5G"));

        testContextHolder().setResponse(response);

    }



}

  /*  given()
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
                        }*/