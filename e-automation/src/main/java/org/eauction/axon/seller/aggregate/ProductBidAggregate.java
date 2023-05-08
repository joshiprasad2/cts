package org.eauction.axon.seller.aggregate;

import java.io.Serializable;

import org.apache.kafka.common.Uuid;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.eauction.axon.events.ProductBidPlacedEvent;
import org.eauction.axon.events.ProductBidUpdatedEvent;
import org.eauction.axon.seller.commands.PlaceProductBidCommand;
import org.eauction.axon.seller.commands.UpdateProductBidCommand;
import org.springframework.beans.BeanUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aggregate
public class ProductBidAggregate implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 4523651603814586105L;

	@AggregateIdentifier
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

	@CommandHandler
	public ProductBidAggregate(PlaceProductBidCommand bidCommand) {
		ProductBidPlacedEvent bidEvent = ProductBidPlacedEvent.builder().build() ;
		bidCommand.setBidId(Uuid.randomUuid().hashCode());
		BeanUtils.copyProperties(bidCommand, bidEvent);
		log.info(" Bid Placed Command : "+bidCommand);
		AggregateLifecycle.apply(bidEvent);
	}
	
	@CommandHandler
	public ProductBidAggregate(UpdateProductBidCommand updateBidCommand) {
		ProductBidUpdatedEvent bidEvent = ProductBidUpdatedEvent.builder().build() ;
		updateBidCommand.setBidId(Uuid.randomUuid().hashCode());
		BeanUtils.copyProperties(updateBidCommand, bidEvent);
		log.info(" Bid Update Command : "+updateBidCommand);
		AggregateLifecycle.apply(bidEvent);
	}
	
	
	public ProductBidAggregate() {}
	
	@EventSourcingHandler
	public void on(ProductBidPlacedEvent bidEvent) {
		log.info("Event Sourcing Handler .");
		this.bidId = bidEvent.getBidId();
		this.firstName = bidEvent.getFirstName();
		this.lastName = bidEvent.getLastName();
		this.address = bidEvent.getAddress(); 
		this.city = bidEvent.getCity();
		this.state = bidEvent.getState();
		this.pin = bidEvent.getPin(); 
		this.phone = bidEvent.getPhone();
		this.email = bidEvent.getEmail(); 
		this.productId = bidEvent.getProductId(); 
		this.bidAmount = bidEvent.getBidAmount();

	}
	
	
	@EventSourcingHandler
	public void on(ProductBidUpdatedEvent bidUpdateEvent) {
		log.info("PRoduct Update Event Sourcing Handler .");
		this.bidId = bidUpdateEvent.getBidId();
		this.email = bidUpdateEvent.getEmail(); 
		this.productId = bidUpdateEvent.getProductId(); 
		this.bidAmount = bidUpdateEvent.getBidAmount();
	}
}
