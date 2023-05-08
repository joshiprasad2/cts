package org.eauction.kafka.config;

import org.springframework.kafka.support.serializer.FailedDeserializationInfo;

public class FailedRecord extends Object{

	private final FailedDeserializationInfo info ;
	private String poisonPill ;
	
	public FailedRecord(FailedDeserializationInfo info) {
		super();
		this.info = info;
	}

	public String getPoisonPill() {
		return poisonPill;
	}

	public void setPoisonPill(String poisonPill) {
		this.poisonPill = poisonPill;
	}
	
	
}
