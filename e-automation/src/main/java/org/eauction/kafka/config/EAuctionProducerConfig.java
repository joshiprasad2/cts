package org.eauction.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.eauction.model.BidRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
public class EAuctionProducerConfig {

	 @Autowired
	 private ObjectMapper objectMapper;
	
	 @Autowired
	 KafkaProperties kafkaBaseProps ;
	 
	@Bean
	public ProducerFactory<String, BidRequest> producerFactory(KafkaConfigProperties kafkaProperties){

		Map<String,Object> props = new HashMap<>() ;
		
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaProperties.getBootStrapServer()) ;
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class) ;
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,JsonSerializer.class) ;
		props.put("schema.registry.url",kafkaProperties.getSchemaRegistry()) ;
		objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS) ;
		
		setSSLProperties(props) ;
		ProducerFactory<String, BidRequest> producerFactory = new DefaultKafkaProducerFactory<>(props, new StringSerializer(), new JsonSerializer<BidRequest>(objectMapper));

		return producerFactory ;
			
	}
	
	@Bean
	public KafkaTemplate<String, BidRequest> kafkaTemplate(KafkaConfigProperties kafkaProperties){
		return new KafkaTemplate<>(producerFactory(kafkaProperties)) ;
	}
	
	private void setSSLProperties(Map<String,Object> props) {
		System.out.println("SSL properties");
		System.out.println(kafkaBaseProps.getSecurity().getProtocol());
		props.put(SslConfigs.SSL_PROTOCOL_CONFIG,kafkaBaseProps.getSecurity().getProtocol()) ;
		props.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG,kafkaBaseProps.getSsl().getKeyStoreLocation().toString()) ;
		props.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG,kafkaBaseProps.getSsl().getKeyStorePassword()) ;
		props.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG,kafkaBaseProps.getSsl().getKeyPassword()) ;
		
		props.put("schema.registry.ssl.keystore.location",kafkaBaseProps.getSsl().getKeyStoreLocation().toString()) ;
		props.put("schema.registry.ssl.keystore.password",kafkaBaseProps.getSsl().getKeyStorePassword()) ;
		props.put("schema.registry.ssl.key.password",kafkaBaseProps.getSsl().getKeyPassword()) ;
		
	}
}
