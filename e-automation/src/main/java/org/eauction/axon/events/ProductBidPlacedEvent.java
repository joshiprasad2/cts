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
public class ProductBidPlacedEvent extends Event implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8509741197712130552L;
	private int bidId ;
	private String firstName;
	private String lastName;
	private String address; 
	private String city ;
	private String state ;
	private int pin ; 
	private String phone ;
	private String email ; 
	private int productId; 
	private double bidAmount ;

}
