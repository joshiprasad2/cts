package org.eauction.kafka;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.eauction.model.AuctionResponse;
import org.eauction.model.BidRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@RunWith(MockitoJUnitRunner.class)
public class KafkaProducerConsumerTest {

	@Mock
	private EAuctionProducer auctionProducer;
	
	@Mock
	private EAuctionConsumer auctionConsumer ;
	
	@Value("${spring.test.topic}")
    private String topic;
	
	 @Test
    public void givenEmbeddedKafkaBroker_whenSendingWithSimpleProducer_thenMessageReceived() 
      throws Exception {
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
        		
       AuctionResponse response =  auctionProducer.publish(bidRequest) ;
       Assert.assertNotNull(response);
       
    }
	 
	@Test
	public void test_eauction_consumer() {
		List<BidRequest> bidReqList = new ArrayList<>() ;
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
        
		bidReqList.add(bidRequest) ;
		
//		auctionConsumer.receive(bidReqList, 0, topic);
		verify(auctionConsumer).receive(bidReqList, 0, topic);
	}
}
