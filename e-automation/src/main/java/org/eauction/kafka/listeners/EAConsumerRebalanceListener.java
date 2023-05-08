package org.eauction.kafka.listeners;

import java.util.Collection;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.ConsumerAwareRebalanceListener;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class EAConsumerRebalanceListener implements ConsumerRebalanceListener, ConsumerAwareRebalanceListener{

	private static final Logger log = LoggerFactory.getLogger(EAConsumerRebalanceListener.class) ;
	
	@Override
	public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
		// TODO Auto-generated method stub
		log.info("Partition Revoked..");
		partitions.stream().forEach(partition ->{
			log.info("Partition Revoked for Topic : "+partition.topic());
		});
	}

	@Override
	public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
		// TODO Auto-generated method stub
		log.info("Partition Assigned..");
		partitions.stream().forEach(partition ->{
			log.info("Partition Assigned for Topic : "+partition.topic());
		});
	}

	public void onParitionRevokedBeforeCommit(Consumer<?,?> consumer,Collection<TopicPartition> partitions) {
		log.info("Partition Revoked Before Commit..");
		partitions.stream().forEach(partition ->{
			log.info("Partition Revoked Before Commit.."+partition.topic());
		});
	}
	
	public void onParitionRevokedAfterCommit(Consumer<?,?> consumer,Collection<TopicPartition> partitions) {
		log.info("Partition Revoked After Commit..");
//		consumer.position(null)
		partitions.stream().forEach(partition ->{
			log.info("Partition Revoked After Commit.."+partition.topic());
		});
	}
	
	public void onParitionAssignedBeforeCommit(Consumer<?,?> consumer,Collection<TopicPartition> partitions) {
		log.info("Partition Assigned Before Commit..");
		partitions.stream().forEach(partition ->{
			log.info("Partition Assigned Before Commit.."+partition.topic());
		});
	}
	
	public void onParitionAssignedAfterCommit(Consumer<?,?> consumer,Collection<TopicPartition> partitions) {
		log.info("Partition Assigned After Commit..");
		partitions.stream().forEach(partition ->{
			log.info("Partition Assigned After Commit.."+partition.topic());
		});
	}
}
