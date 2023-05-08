package org.eauction.axon.events;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProductBidUpdatedEvent extends Event implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1309305001182291670L;
	
	private int bidId ;
	private String email ; 
	private int productId; 
	private double bidAmount ;
}
