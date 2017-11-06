package com.neopragma.restassured;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Sample code for using RESTAssured.
 * 
 * @author dave
 */
public class ApiTestRestAssuredOnly {
	
	private static final String RPN_SERVICE = "http://rpn-service.herokuapp.com";
	
	@Test
	public void itConnectsToTheRpnService() {
		given(). 
		when().  
		    get(RPN_SERVICE).  
		then().  
		    statusCode(200);
	}
	
	@Test
	public void itReturnsNotFoundForUnknownURI() {
		given().
		when().
		    get(RPN_SERVICE + "/bogus/path").  
		then().  
		    statusCode(404);
	}
	
	@Test
	public void itReturnsResultsAsJSON() { 
		given().  
		when().  
		    get(RPN_SERVICE).  
		then().  
		    contentType("application/json");
	}
	
	@Test
	public void itRetrievesUsageHelpForRpnService() {
		String calcValue = 
		given().
		when().
		    get(RPN_SERVICE).
		then().
		    extract(). 
		    path("usage[0].path");
		assertEquals("/calc/*", calcValue);
	}
	
	@Test
	public void itReturnsTheResultOfACalculation() {
		String returnValue = 
		given().  
		when().  
		    get(RPN_SERVICE + "/calc/6/4/5/+/*").  
		then().  
		    extract().  
		    path("rpn.result");  
		assertEquals("54.0", returnValue);
	}

}
