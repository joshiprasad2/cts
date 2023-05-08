package org.eauction.axon.controller;

import javax.net.ssl.SSLContext;

import org.eauction.application.EAuctionApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = {EAuctionApplication.class})
public class BaseRestTestSupport {

	@Value("${server.port}")
	private int localServerPort ;
	
	@Autowired
	private SSLContext sslContext ;
	
	public int getLocalServerPort() { return 8089 ;}
	
	public RequestSpecification getBaseRequest() {
		
		return RestAssured.given()
				.relaxedHTTPSValidation()
				.baseUri("https://localhost:"+8089)
				.port(getLocalServerPort())
				.contentType(MediaType.APPLICATION_JSON_VALUE) ;
	}
}
