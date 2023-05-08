package org.eauction.kafka.error.handler;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.event.NonResponsiveConsumerEvent;

@Configuration
public class CustomKafkaErrorHandler {

	@EventListener()
	public void eventHandler(NonResponsiveConsumerEvent event) {
	    //When Kafka server is down, NonResponsiveConsumerEvent error is caught here.
	    System.out.println("CAUGHT the event "+ event);
	}
}
