//package org.eauction.axon.controller;
//
//import static org.hamcrest.CoreMatchers.equalTo;
//
//import org.axonframework.eventsourcing.eventstore.EventStore;
//import org.eauction.model.AuctionResponse;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//
//import io.restassured.http.ContentType;
//import io.restassured.http.Header;
//import io.restassured.response.Response;
//import lombok.AllArgsConstructor;
//
//@AllArgsConstructor
//@TestMethodOrder(OrderAnnotation.class)
//public class SellerApiControllerTest extends BaseRestTestSupport{
//
//	@Autowired
//	EventStore eventStore ;
//
//	private String token = null ;
//	
//	@Before
//	public void setup() {
////	   baseURI = "http://localhost:8080";
//		JSONObject params = new JSONObject();
//		try {
//	        params.put("userName", "SELLER");
//	        params.put("password", "seller222");
//	    	
//	        Response response = getBaseRequest()
//	                .contentType(ContentType.JSON)
//	                .accept(ContentType.JSON)
//	                .body(params.toString())
//	                .log().all()
//	                .when()
//	                .post("/e-auction/api/v1/user/register")
//	                .then()
//	                .assertThat().statusCode(HttpStatus.OK.value())
//	                .log().all()
//	                .extract()
//	                .response();
//	        Assertions.assertNotNull(response) ;
//	        AuctionResponse auRes= response.getBody().as(AuctionResponse.class);
//	        System.out.println("response : "+response);
//	        token = (String)auRes.getResponse();
//	        
//	  	}catch(JSONException jsonExe) {
//	  		jsonExe.printStackTrace();
//	  	}catch(Exception exe) {
//	  		exe.printStackTrace() ;
//	  	}
//	
//	}
//
//	@Order(1)
//	@Test
//    public void testAddNewProductApi() throws JSONException {
//    	JSONObject params = new JSONObject();
//        params.put("firstName", "firstname");
//        params.put("lastName", "last name");
//        params.put("address", "address");
//        params.put("city", "Mum");
//        params.put("state", "MH");
//        params.put("pin", 411045);
//        params.put("phone", "1234567890");
//        params.put("email", "xxx@gmail.com");
//        params.put("productName", "Wall Sculptor");
//        params.put("detailedDescription", "Contract for wall Sculptor");
//        params.put("shortDescription", "Contract for wall Sculptor");
//        params.put("category", "Sculptor");
//        params.put("startingPrice", 45000.0);
//        params.put("bidEndDate", "2023-05-15 17:10:00");
//        
//    	
//        Response response = getBaseRequest()
//                .contentType(ContentType.JSON)
//                .accept(ContentType.JSON)
//                .header(new Header("Authorization","Bearer "+token))
//                .body(params.toString())
//                .log().all()
//                .when()
//                .post("/e-auction/api/v1/seller/add-product")
//                .then()
//                .assertThat().statusCode(200)
//                .log().all()
//                .extract().
//                response();
//        Assertions.assertNotNull(response) ;
//
////        JsonPath jsonPathObj = response.jsonPath();
////        Assert.assertEquals("", false)
////        jsonPathObj.getString("name"), "User-1"
//    }
//    
//
//	@Order(2)
//	@Test
//	public void testDeleteProduct() {
//		
//		 Response response = getBaseRequest()
//	                .contentType(ContentType.JSON)
//	                .accept(ContentType.TEXT)
//	                .header(new Header("Authorization","Bearer "+token))
//	                .log().all()
//	                .when()
//	                .delete("/delete/"+10)
//	                .then()
//	                .assertThat().statusCode(200)
//	                .body("message", equalTo("Product Deleted."))
//	                .body("isError", equalTo(false))
//	                .log().all()
//	                .extract().
//	                response();
//		 Assertions.assertNotNull(response) ;
//
//	}
//	
//	@Test
//	public void testShowBidApi() {
//		
//		 Response response = getBaseRequest()
//	                .contentType(ContentType.JSON)
//	                .accept(ContentType.TEXT)
//	                .header(new Header("Authorization","Bearer "+token))
//	                .log().all()
//	                .when()
//	                .delete("/show-bids/"+10)
//	                .then()
//	                .assertThat().statusCode(200)
//	                .body("message", equalTo("Product Deleted."))
//	                .body("isError", equalTo(false))
//	                .log().all()
//	                .extract().
//	                response();
//		 Assertions.assertNotNull(response) ;
//
//	}
//}
