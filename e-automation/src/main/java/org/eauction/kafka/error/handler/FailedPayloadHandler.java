package org.eauction.kafka.error.handler;

import java.util.function.Function;

import org.eauction.kafka.config.FailedRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.serializer.FailedDeserializationInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FailedPayloadHandler implements Function<FailedDeserializationInfo, Object>{

	private static final Logger log = LoggerFactory.getLogger(FailedPayloadHandler.class) ;
	
	@Override
	public FailedRecord apply(FailedDeserializationInfo info) {
		// TODO Auto-generated method stub
		log.debug("Failed Payload Handler..");
		String str = new String(info.getData()) ;
		
		FailedRecord record = new FailedRecord(info) ;
		record.setPoisonPill(str);
		
		log.error("FailedPayloadHandler | poison pill detected : "+str);
		log.error("Error : "+info.getException().getMessage());
		
		return record;
	}

}
