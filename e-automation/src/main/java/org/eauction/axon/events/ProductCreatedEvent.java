package org.eauction.axon.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProductCreatedEvent extends Event{

	private Integer productId ;
	private String firstName;
	private String lastName ;
	private String address ;
	private String city  ;
	private String state; 
	private int pin ;
	private String phone ;
	private String email ;
	private String productName ;
	private String shortDescription;
	private String detailedDescription ;
	private String category ;
	private double startingPrice ;
	private String bidEndDate ;

}
