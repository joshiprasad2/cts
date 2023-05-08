package org.eauction.axon.seller.aggregate;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.eauction.axon.events.ProductBidPlacedEvent;
import org.eauction.axon.events.ProductBidUpdatedEvent;
import org.eauction.axon.seller.commands.PlaceProductBidCommand;
import org.eauction.axon.seller.commands.UpdateProductBidCommand;
import org.junit.Before;
import org.junit.Test;

public class ProductBidAggregateTest {

	private FixtureConfiguration<ProductBidAggregate> fixture ;
	
	@Before
	public void setup() {
		fixture = new AggregateTestFixture<>(ProductBidAggregate.class);
	}

	@Test
	public void testPlaceProductBidCommand() {
		PlaceProductBidCommand bidCommand = PlaceProductBidCommand.builder()
				.bidId(123)
				.firstName("fe")
				.lastName("sadf")
				.city("Pune")
				.state("MH")
				.bidAmount(45000.00)
				.email("xyz.14@gmail.com")
				.productId(10)
				.phone("1234567890")
				.pin(411045)
				.build() ;
		
		ProductBidPlacedEvent placedEvent = ProductBidPlacedEvent.builder()
				.bidId(123)
				.firstName("fe")
				.lastName("sadf")
				.city("Pune")
				.state("MH")
				.bidAmount(45000.00)
				.email("xyz.14@gmail.com")
				.productId(10)
				.phone("1234567890")
				.pin(411045)
				.build() ;
		
		fixture.givenNoPriorActivity()
				.when(bidCommand)
				.expectSuccessfulHandlerExecution()
				.expectEvents(placedEvent) ;
	}

	@Test
	public void testUpdateProductBidCommand() {
		UpdateProductBidCommand bidUpdateCommand = UpdateProductBidCommand.builder()
				.bidId(123)
				.bidAmount(45000.00)
				.email("xyz.14@gmail.com")
				.productId(10)
				.build() ;
		
		ProductBidUpdatedEvent updateEvent = ProductBidUpdatedEvent.builder()
				.bidId(123)
				.bidAmount(45000.00)
				.email("xyz.14@gmail.com")
				.productId(10)
				.build() ;
		
		fixture.givenNoPriorActivity()
				.when(bidUpdateCommand)
				.expectSuccessfulHandlerExecution()
				.expectEvents(updateEvent) ;
	}

}
