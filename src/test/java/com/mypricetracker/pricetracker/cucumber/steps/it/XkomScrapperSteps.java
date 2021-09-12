package com.mypricetracker.pricetracker.cucumber.steps.it;

import com.mypricetracker.pricetracker.api.response.SingleProductData;
import com.mypricetracker.pricetracker.domain.product.impl.ProductRepository;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@RequiredArgsConstructor
public class XkomScrapperSteps extends AbstractSteps {

    private final ProductRepository productRepository;

    private static final String SCRAP_ENDPOINT = "/scrap";

    private static String translateNullSentinel(String value) {
        return (value.trim().equals("null")) ? null : value.trim();
    }

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

    @When("User prepares and executes GET request for {string}")
    public void userPreparesAndExecutesGETRequestAsBelow(String productName){
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

    @And("Product name is {string}")
    public void productNameIsProductName(String productName) {
        final Response response = testContextHolder().getResponse();

        Assertions.assertEquals(productName, response.getBody().as(SingleProductData.class).getProductName());
    }

    @And("Every record or the response has the same {string}")
    public void everyRecordOrTheResponseHasTheSameProductName(String productName) {
        final Response response = testContextHolder().getResponse();
        response.then().assertThat().body("responseProductList", notNullValue())
                .body("responseProductList[0].productName", equalTo(productName))
                .and()
                .body("responseProductList[1].productName", equalTo(productName));
    }

    @And("ShopType is {word}")
    public void shoptypeNameIsShopType(String shopType) {
        final Response response = testContextHolder().getResponse();
        response.then().assertThat().body("responseProductList", notNullValue())
                .body("responseProductList[0].shopType", equalTo(shopType))
                .and()
                .body("responseProductList[1].shopType", equalTo(shopType));

    }
}

