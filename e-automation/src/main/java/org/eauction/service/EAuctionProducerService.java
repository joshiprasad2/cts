package org.eauction.service;

import org.auction.avro.PlaceBidRequest;
import org.eauction.exceptions.EAutionGenericException;
import org.eauction.kafka.EAuctionProducer;
import org.eauction.model.AuctionResponse;
import org.eauction.model.BidRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EAuctionProducerService {

	private static final Logger log = LoggerFactory.getLogger(EAuctionProducerService.class) ;
	
	@Autowired
	private EAuctionProducer producer ;
	
	public AuctionResponse placeBidOrder(BidRequest bidReq) {
		AuctionResponse response = new AuctionResponse() ;
		try {
//			PlaceBidRequest placeBid = convertToAvro(bidReq) ;
			producer.publish(bidReq) ;
			log.info("Bid Placed....");
			response.setMessage("Bid Placed.") ;
			response.setResponse(null) ;
			
		}catch(EAutionGenericException ex) {
			log.error("Error OCcured While placing Bid Order. | "+ex.getMessage());
			response.setMessage(ex.getMessage());
			response.setError(true);
		}
		
		return response ;
	}
	
	
	
	public AuctionResponse updateBidOrder(int productId,String buyerEmailld,double newBidAmount) {
		AuctionResponse response = new AuctionResponse() ;
		try {
			//producer.publish(bidReq) ;
//			convertToAvro()
			response.setMessage("Bid Placed.." ) ;
		}catch(EAutionGenericException ex) {
			log.error("Error OCcured While placing Bid Order. | "+ex.getMessage());
			response.setMessage(ex.getMessage());
		}
		
		return response ;
	}
	
	private PlaceBidRequest convertToAvro(BidRequest bidRequest) {
		PlaceBidRequest placeBid = PlaceBidRequest.newBuilder().setFirstName(bidRequest.getFirstName())
				.setLastName(bidRequest.getLastName()).setAddress(bidRequest.getAddress())
				.setState(bidRequest.getState()).setCity(bidRequest.getCity()).setPin(bidRequest.getPin())
				.setPhone(bidRequest.getPhone()).setProductId(bidRequest.getProductId()).setEmail(bidRequest.getEmail())
				.setBidAmount(bidRequest.getBidAmount())
				.build() ;
		
		return placeBid ;
		
	}
}
