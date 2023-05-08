package org.eauction.axon.seller.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlaceProductBidCommand {

	@TargetAggregateIdentifier
	private int bidId ;
	private String firstName;
	private String lastName;
	private String address; 
	private String city ;
	private String state ;
	private int pin ; 
	private String phone ;
	private String email ; 
	private int productId; 
	private double bidAmount ;

}
