package org.eauction.axon.seller.aggregate;

import java.io.Serializable;
import java.util.UUID;

import org.apache.kafka.common.Uuid;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.eauction.axon.events.ProductCreatedEvent;
import org.eauction.axon.seller.commands.ProductCommand;
import org.springframework.beans.BeanUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aggregate
public class ProductAggregate implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -6837578082447553712L;

	@AggregateIdentifier
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

	@CommandHandler
	public ProductAggregate(ProductCommand productCommand) {
		ProductCreatedEvent createEvent = ProductCreatedEvent.builder().build();
		productCommand.setProductId(Integer.parseInt(UUID.randomUUID().toString()));
		BeanUtils.copyProperties(productCommand, createEvent);
		log.info(" Product Command : "+productCommand);
		AggregateLifecycle.apply(createEvent);
	}
	
	
	public ProductAggregate() {}
	
	
	@EventSourcingHandler
	public void on(ProductCreatedEvent productCreatedEvent) {
		log.info("Product Event Sourcing Handler");
		this.productId = productCreatedEvent.getProductId();
		this.firstName = productCreatedEvent.getFirstName();
		this.lastName = productCreatedEvent.getLastName();
		this.address = productCreatedEvent.getAddress();
		this.city  = productCreatedEvent.getCity();
		this.state = productCreatedEvent.getState(); 
		this.pin  = productCreatedEvent.getPin();
		this.phone = productCreatedEvent.getPhone();
		this.email = productCreatedEvent.getEmail();
		this.productName = productCreatedEvent.getProductName();
		this.shortDescription = productCreatedEvent.getShortDescription();
		this.detailedDescription = productCreatedEvent.getDetailedDescription();
		this.category = productCreatedEvent.getCategory();
		this.startingPrice = productCreatedEvent.getStartingPrice();
		this.bidEndDate = productCreatedEvent.getBidEndDate();
	}
}
