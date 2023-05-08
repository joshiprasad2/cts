package org.eauction.axon.seller.query;

import org.axonframework.queryhandling.QueryHandler;
import org.eauction.model.AuctionResponse;
import org.eauction.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ProductBidQueryHandler {


	@Autowired
	private SellerService sellerService ;
	
	@QueryHandler
	public AuctionResponse handle(GetProductBids bidQuery){
//		List<ProductBid> listOfBids = bidRepo.findAll();
		log.info("List of Bids for Product : = "+bidQuery.getProductId());
		AuctionResponse response = sellerService.listAllProductBids(bidQuery.getProductId());
		
		return response ;
	}
}
