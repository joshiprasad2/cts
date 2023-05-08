package org.eauction.axon.events.handlers;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.eauction.axon.events.ProductBidPlacedEvent;
import org.eauction.model.AuctionResponse;
import org.eauction.model.BidRequest;
import org.eauction.service.EAuctionProducerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@ProcessingGroup("ProductBid")
@RequiredArgsConstructor
public class ProductBidPlacedEventHandler {

	@Autowired
	private EAuctionProducerService auctionService ;
	
	@EventHandler
	public void on(ProductBidPlacedEvent bidEvent) {
		log.info(" Bid Placed Event Handler...");
		BidRequest bidRequest = BidRequest.builder().build();
		BeanUtils.copyProperties(bidEvent, bidRequest);
		AuctionResponse res = auctionService.placeBidOrder(bidRequest);
	}
}
