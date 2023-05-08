package org.eauction.kafka.error.handler;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.RetryListener;

import lombok.extern.slf4j.Slf4j;


@Configuration
@Slf4j
public class CustomKafkaRetryListener implements RetryListener{

	private static final Logger log = LoggerFactory.getLogger(CustomKafkaRetryListener.class) ;
	
	@Override
	public void failedDelivery(ConsumerRecord<?, ?> record, Exception ex, int deliveryAttempt) {
		// TODO Auto-generated method stub
		log.error("Retry Error");
		log.error(record.toString());
		log.error(ex.getMessage());
		log.error("Retry Count : "+deliveryAttempt);
	}

}
