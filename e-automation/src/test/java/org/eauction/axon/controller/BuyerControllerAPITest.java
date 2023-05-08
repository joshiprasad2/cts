package org.eauction.axon.controller;

import java.util.HashMap;
import java.util.Map;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.eauction.model.AuctionResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@TestMethodOrder(OrderAnnotation.class)
public class BuyerControllerAPITest extends BaseRestTestSupport{

	private String token = null ;
	private int productId = 0 ;
	private int bidId = 0 ;
	
	@BeforeTest
	public void setup() {
//	   baseURI = "http://localhost:8080";
		JSONObject params = new JSONObject();
		try {
	        params.put("userName", "axon");
	        params.put("password", "axon1234");
	    	
	        Response response = getBaseRequest()
	                .contentType(ContentType.JSON)
	                .accept(ContentType.JSON)
	                .body(params.toString())
	                .log().all()
	                .when()
	                .post("/e-auction/api/v1/user/register")
	                .then()
	                .assertThat().statusCode(HttpStatus.OK.value())
	                .log().all()
	                .extract()
	                .response();
	        Assertions.assertNotNull(response) ;
	        AuctionResponse auRes= response.getBody().as(AuctionResponse.class);
	        System.out.println("response : "+response);
	        token = (String)auRes.getResponse();
	        
	        JSONObject productParam = new JSONObject();
	        params.put("firstName", "firstname");
	        params.put("lastName", "last name");
	        params.put("address", "address");
	        params.put("city", "Mum");
	        params.put("state", "MH");
	        params.put("pin", 411045);
	        params.put("phone", "1234567890");
	        params.put("email", "xxx@gmail.com");
	        params.put("productName", "Wall Sculptor");
	        params.put("detailedDescription", "Contract for wall Sculptor");
	        params.put("shortDescription", "Contract for wall Sculptor");
	        params.put("category", "Sculptor");
	        params.put("startingPrice", 45000.0);
	        params.put("bidEndDate", "2023-05-15 17:10:00");
	        
	    	
	        Response prodRes = getBaseRequest()
	                .contentType(ContentType.JSON)
	                .accept(ContentType.JSON)
	                .header(new Header("Authorization","Bearer "+token))
	                .body(productParam.toString())
	                .log().all()
	                .when()
	                .post("/e-auction/api/v1/seller/add-product")
	                .then()
	                .assertThat().statusCode(200)
	                .log().all()
	                .extract().
	                response();
	        Assertions.assertNotNull(prodRes) ;
	        AuctionResponse prodAuctionRes = prodRes.getBody().as(AuctionResponse.class);
	       String resObj = (String) prodAuctionRes.getResponse();
	       String prdId= resObj.substring(resObj.indexOf(":")+1, resObj.indexOf("|")) ;
	       productId = Integer.parseInt(prdId);
	       log.info("productId = "+productId);
	  	}catch(JSONException jsonExe) {
	  		jsonExe.printStackTrace();
	  	}catch(Exception exe) {
	  		exe.printStackTrace() ;
	  	}
	
	}

	@Autowired
	EventStore eventStore ;
	
	@Ignore
	@Order(2)
    @Test
    public void testPlaceBidApi() throws JSONException {
    	JSONObject params = new JSONObject();
        params.put("firstName", "firstname");
        params.put("lastName", "last name");
        params.put("address", "address");
        params.put("city", "Mum");
        params.put("state", "MH");
        params.put("pin", 411045);
        params.put("phone", "1234567890");
        params.put("email", "xxx@gmail.com");
        params.put("productId", productId);
        params.put("bidAmount", 45000.00);
//        params.put("bidId", "123");
    
        Map<String,String> headers = new HashMap<>();
        headers.put("Accept", token);
        
        Response response = getBaseRequest()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header(new Header("Authorization","Bearer "+token))
                .body(params.toString())
                .log().all()
                .when()
                .post("/e-auction/api/v1/buyer/place-bid")
                .then()
                .assertThat().statusCode(HttpStatus.OK.value())
//                .body("message", equalTo("Bid Placed Successfully. "))
//                .body("isError", equalTo(false))
                .log().all()
                .extract()
                .response();
        Assertions.assertNotNull(response) ;
        AuctionResponse auRes= response.getBody().as(AuctionResponse.class);
        Assert.assertNotNull(auRes);
        bidId = (Integer)auRes.getResponse() ;
        log.info("Res message: "+auRes.getMessage()) ;
        log.info("Res obj: "+auRes.getResponse()) ;
//        JsonPath jsonPathObj = response.jsonPath();
//        Assert.assertEquals("", false)
//        jsonPathObj.getString("name"), "User-1"
    }
    
	@Ignore
	@Order(3)
    @Test
    public void testUpdateBidApi() throws JSONException {
       
    	String email = "xxx@gmail.com" ;
    	
        Response response = getBaseRequest()
                .contentType(ContentType.JSON)
                .accept(ContentType.TEXT)
                .header(new Header("Authorization","Bearer "+token))
                .log().all()
                .when()
                .post("/update-bid/"+ 123 +"/"+email+"/52000.0")
                .then()
                .assertThat().statusCode(HttpStatus.OK.value())
//                .body("message", equalTo("Bid Updated Successfully. "))
//                .body("isError", equalTo(false))
                .log().all()
                .extract().
                response();

        Assertions.assertNotNull(response) ;
        AuctionResponse auRes= response.getBody().as(AuctionResponse.class);
        Assert.assertNotNull(auRes);
        log.info("Res message: "+auRes.getMessage()) ;
        log.info("Res obj: "+auRes.getResponse()) ;

//        JsonPath jsonPathObj = response.jsonPath();
//        Assert.assertEquals("", false)
//        jsonPathObj.getString("name"), "User-1"
    }
    
}
