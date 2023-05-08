package org.eauction.kafka;

import java.util.List;

import org.eauction.exceptions.EAutionGenericException;
import org.eauction.model.AuctionResponse;
import org.eauction.model.BidRequest;
import org.eauction.service.EAuctionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class EAuctionConsumer {
	
	private static final Logger log = LoggerFactory.getLogger(EAuctionConsumer.class) ;
	
	@Autowired
	EAuctionService auctionService ;
	
	// IF we are planning to start and stop consumer on some event through listener or API then
	// only use this handler. and autoStartup property else remove it
//	@Autowired
//	private HandleKafkaStartStopListener listenerHandler ;
	
	
	@KafkaListener(topics = "${spring.kafka.topicName}",groupId = "${spring.kafka.consumer.group-id}",
			autoStartup = "true",id = "bidReceiver")
	public void receive(@Payload List<BidRequest> bidReqList, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
			@Header(KafkaHeaders.RECEIVED_TOPIC) String topicName) {
		log.info("consumer ...");
		try {
			log.info("starting consumer...");
//			listenerHandler.startListener("bidReceiver");
			bidReqList.stream().forEach(bidReq -> {
				AuctionResponse response  = auctionService.persistBidData(bidReq) ;
				log.debug(" Bid Request Placed. "+response.getResponse()) ;
			});
			
		}catch(EAutionGenericException ex) {
			
			log.error("Error Occured While Consuming the Bid."+ex.getMessage());
		}
	}
}
