package org.eauction.kafka;

import java.util.concurrent.ExecutionException;

import org.eauction.kafka.config.KafkaConfigProperties;
import org.eauction.model.AuctionResponse;
import org.eauction.model.BidRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class EAuctionProducer {

	private static final Logger log = LoggerFactory.getLogger(EAuctionProducer.class) ;
	
	@Autowired
	private KafkaConfigProperties kafkaProp ;

	@Autowired
	private KafkaTemplate<String, BidRequest> kafkaTemplate ;
	
	public AuctionResponse publish(BidRequest bidRequest) {
		log.info(bidRequest.toString());
		AuctionResponse response = new AuctionResponse() ;
		try {
			
//			Message<BidRequest> message =  MessageBuilder.withPayload(bidRequest).setHeader(KafkaHeaders.TOPIC,kafkaProp.getTopicName())
//					.build() ;
			log.info("topic Name : "+kafkaProp.getTopicName());		
			ListenableFuture<SendResult<String, BidRequest>> future =  this.kafkaTemplate.send(kafkaProp.getTopicName(),bidRequest) ;	
			log.info(future.get().getProducerRecord().toString()) ;
			future.addCallback(new ListenableFutureCallback<SendResult<String, BidRequest>>() {

				@Override
				public void onSuccess(SendResult<String, BidRequest> result) {
					// TODO Auto-generated method stub
					response.setMessage("Bid Placed..");
					response.setResponse(result.getProducerRecord().value().toString());
				}

				@Override
				public void onFailure(Throwable ex) {
					// TODO Auto-generated method stub
					response.setMessage("Failed to Place Bid..");
					response.setResponse(ex.getMessage());
				}
			});
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			response.setMessage(e.getMessage());
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			response.setMessage(e.getMessage());
		}
		
		return response ;
	}
}
