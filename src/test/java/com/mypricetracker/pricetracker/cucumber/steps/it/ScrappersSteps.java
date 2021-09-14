package com.mypricetracker.pricetracker.cucumber.steps.it;

import com.mypricetracker.pricetracker.api.response.SingleProductData;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@RequiredArgsConstructor
public class ScrappersSteps extends AbstractSteps {

    private static final String SCRAP_ENDPOINT = "/scrap";

    private static String translateNullSentinel(String value) {
        return (value.trim().equals("null")) ? null : value.trim();
    }

    //POST STEPS
    @When("User prepares and executes POST request as below:")
    public void userPreparesAndExecutesPOSTRequestAsBelow(DataTable dataTable) throws JSONException {
        final String executionUrl = baseUrl() + SCRAP_ENDPOINT;
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        JSONObject requestBody = new JSONObject();
        for (Map<String, String> columns : rows) {
            requestBody.put("url", translateNullSentinel(columns.get("url")));
            requestBody.put("borderPrice", translateNullSentinel(columns.get("borderPrice")));
        }

        testContextHolder().setPayload(requestBody.toString());

        final Response response = given()
                .body(testContextHolder().getPayload())
                .contentType(ContentType.JSON)
                .log()
                .all()
                .when()
                .post(executionUrl);

        testContextHolder().setResponse(response);

    }

    @SneakyThrows
    @And("Field {word} name is {string}")
    public void productNameIsProductName(String fieldName, String fieldValue) {
        final Response response = testContextHolder().getResponse();
        SingleProductData singleProductData = response.getBody().as(SingleProductData.class);

        Field reflectedField = singleProductData.getClass().getDeclaredField(fieldName);
        reflectedField.setAccessible(true);
        Assertions.assertEquals(fieldValue, reflectedField.get(singleProductData));
    }

    @SneakyThrows
    @And("Field {word} is {word}")
    public void wordFieldAssertion(String fieldName, String fieldValue) {
        final Response response = testContextHolder().getResponse();
        SingleProductData singleProductData = response.getBody().as(SingleProductData.class);

        Field reflectedField = singleProductData.getClass().getDeclaredField(fieldName);
        reflectedField.setAccessible(true);
        Assertions.assertEquals(fieldValue, reflectedField.get(singleProductData));
    }

    //GET STEPS
    @When("User prepares and executes GET request for {string}")
    public void userPreparesAndExecutesGETRequestAsBelow(String productName) {
        final String executionUrl = baseUrl() + SCRAP_ENDPOINT;

        final Response response = given()
                .contentType(ContentType.JSON)
                .param("productName", productName)
                .log()
                .all()
                .when()
                .get(executionUrl);

        testContextHolder().setResponse(response);

    }

    //TODO you just check first two
    @And("Every record of the response has the same productName: {string}")
    public void everyRecordOrTheResponseHasTheSameProductName(String productName) {
        final Response response = testContextHolder().getResponse();
        response.then().assertThat().body("responseProductList", notNullValue())
                .body("responseProductList[0].productName", equalTo(productName))
                .and()
                .body("responseProductList[1].productName", equalTo(productName));
    }

    @And("Every record of the response has the {word}")
    public void everyRecordOrTheResponseHasTheSameShopType(String shopType) {
        final Response response = testContextHolder().getResponse();
        response.then().assertThat().body("responseProductList", notNullValue())
                .body("responseProductList[0].shopType", equalTo(shopType))
                .and()
                .body("responseProductList[1].shopType", equalTo(shopType));
    }


}

