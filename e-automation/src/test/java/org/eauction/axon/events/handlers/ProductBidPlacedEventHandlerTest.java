package org.eauction.axon.events.handlers;

import static org.mockito.Mockito.verify;

import org.eauction.axon.events.ProductBidPlacedEvent;
import org.eauction.model.BidRequest;
import org.eauction.service.EAuctionProducerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

@RunWith(MockitoJUnitRunner.class)
public class ProductBidPlacedEventHandlerTest {

	@Mock
	private EAuctionProducerService auctionService ;
	
	private ProductBidPlacedEventHandler bidPlacedEventHandler;
	
	@Before
	public void setup() {
		bidPlacedEventHandler = new ProductBidPlacedEventHandler() ;
	}
	
	@Test
	public void testBidPlacedEventHandler() {
		
		ProductBidPlacedEvent bidPlacedEvent = ProductBidPlacedEvent.builder()
				.bidId(120)
				.firstName("firstname")
				.lastName("lastname")
				.address("asdf")
				.city("pune")
				.state("sdf")
				.pin(41253)
				.phone("123567890")
				.email("xxx@gmailc.com")
				.productId(10)
				.bidAmount(45000.0)
				.build() ;
		
		bidPlacedEventHandler.on(bidPlacedEvent);
		
		BidRequest bidRequest = BidRequest.builder().build();
		BeanUtils.copyProperties(bidPlacedEvent, bidRequest);
		
		verify(auctionService).placeBidOrder(bidRequest);
	}
}
