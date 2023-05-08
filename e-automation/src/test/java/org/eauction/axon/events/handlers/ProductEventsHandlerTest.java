package org.eauction.axon.events.handlers;

import static org.mockito.Mockito.verify;

import org.eauction.axon.events.ProductCreatedEvent;
import org.eauction.model.ProductRequest;
import org.eauction.service.SellerService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

@RunWith(MockitoJUnitRunner.class)
public class ProductEventsHandlerTest {

	@Mock
	private SellerService sellerService ;
	
	private ProductEventsHandler eventsHandler ;
	
	@Before
	public void setup() {
		eventsHandler = new ProductEventsHandler() ;
	}
	
	public void testProductCreatedEvent() {
		ProductCreatedEvent createdEvent = ProductCreatedEvent.builder()
				.address("asdf")
				.bidEndDate("asdf")
				.category("Sculptor")
				.phone("1234567890")
				.email("xxx@gmail.com")
				.productId(10)
				.build();
		eventsHandler.on(createdEvent);
		
		ProductRequest productRequest = ProductRequest.builder().build();
		BeanUtils.copyProperties(createdEvent, productRequest);
		verify(sellerService).addNewProduct(productRequest) ;
		
	}
}
