package org.eauction.service;

import org.eauction.kafka.EAuctionProducer;
import org.eauction.model.AuctionResponse;
import org.eauction.model.BidRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EAuctionProducerServiceTest {

	@InjectMocks
	private EAuctionProducerService auctionProducerService;
	
	@Mock
	private EAuctionProducer producer ;
	
	@Test
	public void testPlaceBidOrder() {
		BidRequest bidRequest = BidRequest.builder()
		.firstName("firstname")
		.lastName("lastname")
		.address("asdf")
		.city("pune")
		.state("sdf")
		.pin(41253)
		.phone("123567890")
		.email("xxx@gmailc.com")
		.productId(10)
		.bidAmount(45000.0).build() ;
		
		AuctionResponse response = new AuctionResponse();
		response.setError(false);
		response.setMessage("Bid Placed Sucessfully.");
		
		Mockito.when(producer.publish(bidRequest)).thenReturn(response) ;
		auctionProducerService.placeBidOrder(bidRequest) ;
		
		Assertions.assertNotNull(response);
		
	}

	
	@Test
	public void testUpdateBidOrder() {
		AuctionResponse response = new AuctionResponse();
		response.setError(false);
		response.setMessage("Bid Updated Sucessfully.");
		
		Mockito.when(auctionProducerService.updateBidOrder(Mockito.anyInt(),Mockito.anyString(),Mockito.anyDouble())).thenReturn(response) ;
		auctionProducerService.updateBidOrder(10,"xxx@gmailc.com",520000.0) ;
		
		Assertions.assertNotNull(response);
		
	}

}
