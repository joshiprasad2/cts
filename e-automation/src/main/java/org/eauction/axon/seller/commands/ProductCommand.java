package org.eauction.axon.seller.commands;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductCommand {

	@TargetAggregateIdentifier
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
