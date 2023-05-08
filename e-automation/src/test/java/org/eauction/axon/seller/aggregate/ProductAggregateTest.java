package org.eauction.axon.seller.aggregate;

import java.util.Date;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.eauction.axon.events.ProductCreatedEvent;
import org.eauction.axon.seller.commands.ProductCommand;
import org.junit.Before;

public class ProductAggregateTest {

private FixtureConfiguration<ProductAggregate> fixture ;
	
	@Before
	public void setup() {
		fixture = new AggregateTestFixture<>(ProductAggregate.class);
	}

	public void testProductCreatedCommand() {
		ProductCommand command = ProductCommand.builder()
				.firstName("fe")
				.lastName("sadf")
				.city("Pune")
				.state("MH")
				.email("xyz.14@gmail.com")
				.productId(10)
				.phone("1234567890")
				.pin(411045)
				.address("")
				.productName("Wall Sculptor")
				.productId(1)
				.shortDescription("Contract for wall Sculptor")
				.detailedDescription("Contract for wall Sculptor")
				.category("Sculptor")
				.bidEndDate("2023-05-15 17:10:00")
				.startingPrice(450000.00)
				.build();
		
		ProductCreatedEvent event = ProductCreatedEvent.builder()
				.firstName("fe")
				.lastName("sadf")
				.city("Pune")
				.state("MH")
				.email("xyz.14@gmail.com")
				.productId(10)
				.phone("1234567890")
				.pin(411045)
				.address("")
				.productName("Wall Sculptor")
				.productId(1)
				.shortDescription("Contract for wall Sculptor")
				.detailedDescription("Contract for wall Sculptor")
				.category("Sculptor")
				.bidEndDate("2023-05-15 17:10:00")
				.startingPrice(450000.00)
				.build() ;
		
		fixture.givenNoPriorActivity()
			.when(command)
			.expectSuccessfulHandlerExecution()
			.expectEvents(event) ;
				
	}
}
