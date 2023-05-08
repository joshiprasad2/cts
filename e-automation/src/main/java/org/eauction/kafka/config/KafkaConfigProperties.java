package org.eauction.kafka.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.validation.annotation.Validated;

import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ConfigurationProperties(prefix = "spring.kafka")
@Validated
@ConfigurationPropertiesScan
@ToString
public class KafkaConfigProperties {

	private String bootStrapServer;
	private String schemaRegistry ;
	private String topicName ;

	
	public String getBootStrapServer() {
		return bootStrapServer;
	}
	public void setBootStrapServer(String bootStrapServer) {
		this.bootStrapServer = bootStrapServer;
	}
	public String getSchemaRegistry() {
		return schemaRegistry;
	}
	public void setSchemaRegistry(String schemaRegistry) {
		this.schemaRegistry = schemaRegistry;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	
}
