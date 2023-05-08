package org.eauction.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCTS")
public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6266934216791544898L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PRODUCT_ID")
	private int productId;
	
	@Column(name="PRODUCT_NAME",unique = true)
	private String productName ;
	
	@Column(name="SHORT_DESCRIPTION",unique = true)
	private String shortDescription;
	
	@Column(name="DETAILED_DESCRIPTION")
	private String detailedDescription ;
	
	@Column(name="CATEGORY",unique = false)
	private String category ;
	
	@Column(name="STARTING_PRICE")
	private double startingPrice ;

//	@CreationTimestamp
	@Column(name="BID_END_DATE")
	private Timestamp bidEndDate ;

	@Column(name="SELLER_ID")
	private int sellerId;

	
	public Product(int productId, String productName, String shortDescription, String detailedDescription,
			String category, double startingPrice, Timestamp bidEndDate, int sellerId) {
		this.productId = productId;
		this.productName = productName;
		this.shortDescription = shortDescription;
		this.detailedDescription = detailedDescription;
		this.category = category;
		this.startingPrice = startingPrice;
		this.bidEndDate = bidEndDate;
		this.sellerId = sellerId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getDetailedDescription() {
		return detailedDescription;
	}

	public void setDetailedDescription(String detailedDescription) {
		this.detailedDescription = detailedDescription;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getStartingPrice() {
		return startingPrice;
	}

	public void setStartingPrice(double startingPrice) {
		this.startingPrice = startingPrice;
	}

	public Timestamp getBidEndDate() {
		return bidEndDate;
	}

	public void setBidEndDate(Timestamp bidEndDate) {
		this.bidEndDate = bidEndDate;
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
}
