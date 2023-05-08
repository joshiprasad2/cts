package org.eauction.axon.events.handlers;

import static org.mockito.Mockito.verify;

import org.eauction.axon.events.ProductBidUpdatedEvent;
import org.eauction.service.EAuctionProducerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProductBidUpdateEventHandlerTest {

	@Mock
	private EAuctionProducerService auctionService ;
	
	private ProductBidUpdateEventHandler bidUpdateEventHandler ;
	
	@Before
	public void setup() {
		bidUpdateEventHandler = new ProductBidUpdateEventHandler() ;
	}
	
	@Test
	public void testUpdateProductBidEvnetHandler() {
		ProductBidUpdatedEvent updatedEvent = ProductBidUpdatedEvent.builder()
				.bidAmount(52000.0)
				.bidId(1)
				.email("xxx@gmail.com")
				.productId(10)
				.build() ;
		bidUpdateEventHandler.on(updatedEvent);
		
		verify(auctionService).updateBidOrder(10, "xxx@gmail.com", 52000.0);
	}
	
}
