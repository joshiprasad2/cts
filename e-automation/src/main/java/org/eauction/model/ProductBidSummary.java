package org.eauction.model;

import java.util.List;

public class ProductBidSummary {

	private ProductDetails products;
	private List<BidRequest> bidDetails ;
	public ProductDetails getProducts() {
		return products;
	}
	public void setProducts(ProductDetails products) {
		this.products = products;
	}
	public List<BidRequest> getBidDetails() {
		return bidDetails;
	}
	public void setBidDetails(List<BidRequest> bidDetails) {
		this.bidDetails = bidDetails;
	}
	public ProductBidSummary(ProductDetails products, List<BidRequest> bidDetails) {
		super();
		this.products = products;
		this.bidDetails = bidDetails;
	}
	public ProductBidSummary() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
