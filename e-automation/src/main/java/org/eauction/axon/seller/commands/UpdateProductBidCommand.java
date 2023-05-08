package org.eauction.axon.seller.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateProductBidCommand {

	@TargetAggregateIdentifier
	private int bidId ;
	private String email ; 
	private int productId; 
	private double bidAmount ;
}
