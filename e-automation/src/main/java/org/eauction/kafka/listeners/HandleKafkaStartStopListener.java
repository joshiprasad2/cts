package org.eauction.kafka.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.stereotype.Service;

@Service
public class HandleKafkaStartStopListener {

	@Autowired
	private KafkaListenerEndpointRegistry endpointRegistry ;
	
	public void startListener(String listenerId) {
		endpointRegistry.getListenerContainer(listenerId).start();
	}
	
	public void stopListener(String listenerId) {
		endpointRegistry.getListenerContainer(listenerId).stop();
	}
}
