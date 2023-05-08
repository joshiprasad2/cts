package org.eauction.axon.config;

import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.EventMessageHandler;
import org.axonframework.eventhandling.ListenerInvocationErrorHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AxonErrorHandlerConfig implements ListenerInvocationErrorHandler{

	@Override
	public void onError(Exception exception, EventMessage<?> event, EventMessageHandler eventHandler) throws Exception {
		// TODO Auto-generated method stub
		log.info("Custom Error Handler : {}");
		log.error("Error From Error Handler : {}"+exception.getMessage()+" | For Event : "+event.getIdentifier());
	}

}
