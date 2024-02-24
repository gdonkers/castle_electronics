package com.castle.cucumber;

import com.castle.service.model.IdGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;

import static com.castle.cucumber.RestClient.request;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


public class StepDefs extends SpringCucumberTestBase {

    @Autowired
    private IdGenerator testIdGenerator;

    private Response response;


    @When("^call (DELETE|POST|GET|UPDATE) on (.*)")
    public void invokeWebCall(Method method, String path) {
        response = request(port).request(method, path);
    }

    @Then("^the response json should be:$")
    public void theResponseJsonShouldBe(String expectedJson) throws JSONException {
        String actualJson = response.getBody().prettyPrint();

        System.out.println("Received response " + actualJson);

        JSONAssert.assertEquals(actualJson, expectedJson, true);
    }

    @Then("^the response json should contain '(.*)' at path '(.*)'")
    public void theResponseJsonShouldContain(String expectedValue, String jsonPathToValue) {
        JsonPath jsonPath = response.getBody().jsonPath();

        String actualValue = jsonPath.get(jsonPathToValue);

        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Then("^the response status should be (.*)")
    public void theResponseStatusShouldBe(Integer expectedStatus) {
        int actualStatus = response.statusCode();

        assertThat(actualStatus).isEqualTo(expectedStatus);
    }

    @When("^POST json to (.*):$")
    public void postJsonTo(String path, String requestJson) {
        response = request(port).body(requestJson).post(path);
    }

    @Given("^Id Generator returns (.*)")
    public void idGeneratorReturns(String id) {

        when(testIdGenerator.createId()).thenReturn(id);
    }

    @Given("an empty database")
    public void anEmptyDatabase() {
        basketProductsRepository.deleteAll();
        discountDealsRepository.deleteAll();
        productRepository.deleteAll();
    }
}
