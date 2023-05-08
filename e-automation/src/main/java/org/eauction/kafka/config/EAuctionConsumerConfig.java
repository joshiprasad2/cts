package org.eauction.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.eauction.kafka.error.handler.CustomKafkaRetryListener;
import org.eauction.kafka.error.handler.FailedPayloadHandler;
import org.eauction.kafka.listeners.EAConsumerRebalanceListener;
import org.eauction.model.BidRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.ContainerProperties.AckMode;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
@EnableKafka
public class EAuctionConsumerConfig {

	private static final Logger log = LoggerFactory.getLogger(EAuctionConsumerConfig.class) ;
	
	@Autowired
	KafkaProperties kafkaBaseProps ;
	
	@Autowired
	CustomKafkaRetryListener retryListener ;
	
	@Autowired
	EAConsumerRebalanceListener rebalanceListener ;
	
	
	@Bean
	public ConsumerFactory<String,BidRequest> consumerFactory(KafkaConfigProperties kafkaProperties){
		Map<String,Object> props = new HashMap<>() ;
		
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaProperties.getBootStrapServer()) ;
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class) ;
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,JsonDeserializer.class) ;
		props.put(ConsumerConfig.GROUP_ID_CONFIG,kafkaBaseProps.getConsumer().getGroupId()) ;
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,kafkaBaseProps.getConsumer().getAutoOffsetReset()) ; 
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"true") ;
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000") ;
		props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,"20") ;
		props.put("schema.registry.url",kafkaProperties.getSchemaRegistry()) ;
		props.put("spring.json.trusted.packages","*") ;
		
		props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS,StringDeserializer.class) ;
		props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS,JsonDeserializer.class) ;
		props.put(ErrorHandlingDeserializer.VALUE_FUNCTION,FailedPayloadHandler.class) ; //for posion pill
		
		setSSLProperties(props) ;
		
		log.info(props.toString());
		return new DefaultKafkaConsumerFactory<>(props) ;
	}

	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, BidRequest> kafkaListenerContainerFactory(KafkaConfigProperties kafkaProperties){
		
		ConcurrentKafkaListenerContainerFactory<String, BidRequest> factory = new ConcurrentKafkaListenerContainerFactory<String, BidRequest>();
		
		FixedBackOff backOff = new FixedBackOff() ;
		backOff.setMaxAttempts(3);
//		backOff.setInterval(1000);
		
		DefaultErrorHandler errorHandler = new DefaultErrorHandler(backOff) ;
		errorHandler.setRetryListeners(retryListener);
		factory.setConsumerFactory(consumerFactory(kafkaProperties));
		factory.setConcurrency(2);
		factory.setBatchListener(true);
		factory.setCommonErrorHandler(errorHandler);
		
		ContainerProperties containerProperties = factory.getContainerProperties() ;
		containerProperties.setAckMode(AckMode.BATCH);
		containerProperties.setPollTimeout(1000);
		containerProperties.setNoPollThreshold(1000);
		containerProperties.setConsumerRebalanceListener(rebalanceListener);
		return factory ;
	}
	
	
//	@Bean
//	public KafkaListenerErrorHandler listenerEH(DeadLetterPublishingRecoverer recoverer) {
//		return (msg,ex) -> {
//			if(msg.getHeaders().get(KafkaHeaders.DELIVERY_ATTEMPT,Integer.class) > 9) {
//				recoverer.accept(msg.getHeaders().get(KafkaHeaders.RAW_DATA,ConsumerRecord.class),ex);
//				return "FAILED" ;
//			}
//			throw ex ;
//		};
//	}
//	
	private void setSSLProperties(Map<String,Object> props) {
		System.out.println("SSL properties");
		System.out.println(kafkaBaseProps.getSecurity().getProtocol());
		System.out.println(kafkaBaseProps.getSsl().getKeyStoreLocation().toString());
		System.out.println(kafkaBaseProps.getSsl().getKeyStorePassword());
		
		props.put(SslConfigs.SSL_PROTOCOL_CONFIG,kafkaBaseProps.getSecurity().getProtocol()) ;
		props.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG,kafkaBaseProps.getSsl().getKeyStoreLocation().toString()) ;
		props.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG,kafkaBaseProps.getSsl().getKeyStorePassword()) ;
		props.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG,kafkaBaseProps.getSsl().getKeyPassword()) ;
		
		props.put("schema.registry.ssl.keystore.location",kafkaBaseProps.getSsl().getKeyStoreLocation().toString()) ;
		props.put("schema.registry.ssl.keystore.password",kafkaBaseProps.getSsl().getKeyStorePassword()) ;
		props.put("schema.registry.ssl.key.password",kafkaBaseProps.getSsl().getKeyPassword()) ;
			
	}
	
}
