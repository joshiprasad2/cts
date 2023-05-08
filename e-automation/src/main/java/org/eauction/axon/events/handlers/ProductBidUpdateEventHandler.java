package org.eauction.axon.events.handlers;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.eauction.axon.events.ProductBidUpdatedEvent;
import org.eauction.service.EAuctionProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@ProcessingGroup("ProductBid")
public class ProductBidUpdateEventHandler {

	@Autowired
	private EAuctionProducerService auctionService ;
	
	@EventHandler
	public void on(ProductBidUpdatedEvent bidUpdateEvent) {
		log.info(" Update Bid Event Handler...");
		
		auctionService.updateBidOrder(bidUpdateEvent.getProductId(),bidUpdateEvent.getEmail(),bidUpdateEvent.getBidAmount());
		
	}
}
