package com.neopragma.restassured;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;

public class ApiTestRestAssuredAndCucumber {
	
	private String valuesToPush;
	private Response response;
	private static final String RPN_SERVICE_BASE_URI = "http://rpn-service.herokuapp.com";
	private static final String EMPTY_STRING = "";
	private static final String UTF8 = "UTF-8";
	private static final String slash = "/";

    @Given("^I want to know how to call the RPN service$") 
    public void setUpEmptyRestfulPath() {
    	valuesToPush = EMPTY_STRING;
    }
    
    @When("^I invoke the RPN service$")
    public void invokeTheRpnService() {
    	response = given().when().get(RPN_SERVICE_BASE_URI + valuesToPush);
    	
    	dump(response, "input");
    	dump(response, "expression");
    	dump(response, "result");
    }
    
    private void dump(Response response, String key) {
    	System.out.println(key + ": " + (String) response.body().jsonPath().get("rpn." + key));
    }
    
    @Then("^I receive usage documentation$")
    public void assertUsageDocumentation() {    	
    	assertTrue(((String) response
       			.body()
       			.jsonPath()
       			.get("usage[0].description"))
       			.startsWith("pass values in postfix order"));
    }
    
	@Given("^I push \"([^\"]*)\"$")
	public void startUriString(String value) {
        valuesToPush = "/calc/" + value;
	}
    
	@Given("\"([^\"]*)\"$")
	public void appendToUriString(String value) {
        valuesToPush += slash + value;
	}

	@Then("^the result is \"([^\"]*)\"$")
	public void assertResult(String result) {
        assertEquals(result, (String) response 
 			.body()
 			.jsonPath()
	        .get("rpn.result"));
	}
	
    private String encode(String value) {
    	try {
			return URLEncoder.encode(value, UTF8);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
    }
    
    
}
