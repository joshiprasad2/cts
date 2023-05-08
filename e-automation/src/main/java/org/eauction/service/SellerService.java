package org.eauction.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eauction.entity.Product;
import org.eauction.entity.ProductBid;
import org.eauction.entity.SellerInformation;
import org.eauction.exceptions.EAutionGenericException;
import org.eauction.model.AuctionResponse;
import org.eauction.model.BidRequest;
import org.eauction.model.ProductBidSummary;
import org.eauction.model.ProductDetails;
import org.eauction.model.ProductRequest;
import org.eauction.repos.ProductBidRepository;
import org.eauction.repos.ProductRepository;
import org.eauction.repos.SellerRepository;
import org.eauction.utils.DateUtility;
import org.eauction.utils.ProductCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

	private static final Logger log = LoggerFactory.getLogger(SellerService.class) ;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private SellerRepository sellerRepo ;
	
	@Autowired
	private ProductBidRepository bidRepo ;
	
	public AuctionResponse addNewProduct(ProductRequest productReq) {
		AuctionResponse response = new AuctionResponse() ;
		try {
			log.debug("Adding new Product");
			Product productEntity = mapProductEntity(productReq) ;
			SellerInformation sellerEntity = mapSellerEntity(productReq) ;
			
			sellerEntity = sellerRepo.save(sellerEntity) ;
			int sellerId = sellerEntity.getSellerId() ;
			log.debug("Seller Id : "+sellerId);
			if(sellerId > 0) {
				productEntity.setSellerId(sellerId);
				productEntity = productRepo.save(productEntity) ;
			}
			log.debug("Product Id: "+productEntity.getProductId());
			response.setMessage("Product Added Successfully.");
			response.setResponse("Product Id : "+productEntity.getProductId()+"| Seller Id :"+productEntity.getSellerId());
		}catch(EAutionGenericException eaexe) {
			log.error("Error Occured While adding Product & Seller Information");
			response.setMessage("Error Occured While adding Product & Seller Information");
			response.setResponse(eaexe.getMessage());
		}
		
		return response ;
	}
	
	public AuctionResponse deleteProduct(int productId) {
		AuctionResponse response = new AuctionResponse() ;
		try {
			Optional<Product> productEntity = productRepo.findById(productId) ;
			
			if(validateBidDetails(productId)) {
				log.debug("Product "+productId+" deleted.");
				productRepo.delete(productEntity.get());
				response.setMessage("Product "+productId+" deleted.");
				response.setResponse("");
			}else {
				log.info("Product Cannot be deleted as It has valid Bid Details.");
				response.setMessage("Product Cannot be deleted as It has valid Bid Details.");
				response.setResponse("");
			}
		}catch(EAutionGenericException eaexe) {
			log.error("Error Occured While deleting product: "+eaexe.getMessage());
			response.setMessage("Error Occured While deleting product :"+productId);
			response.setResponse(eaexe.getMessage());
		}
		
		return response ;
	}
	
	public AuctionResponse listAllProductBids(int productId) {
		AuctionResponse response = new AuctionResponse() ;
		try {
			Product product = getProductDetails(productId) ;
			List<ProductBid> list = bidRepo.findAllBids(productId) ;
			log.debug(" product bids = "+list);
			
			List<BidRequest> bidSummary = list.stream()
	        	.map(dto ->  new BidRequest(dto.getFirstName(),dto.getLastName(),dto.getAddress(),dto.getCity(),dto.getState(),
	        			dto.getPin(),dto.getPhone(),dto.getEmail(),dto.getProductId(),dto.getBidAmount()))
	        	.sorted(Comparator.comparingDouble(BidRequest::getBidAmount).reversed())
				.collect(Collectors.toList());
			 
			LocalDateTime bidEndDate = DateUtility.convertSqlToUtilDate(product.getBidEndDate()) ;
			
			ProductDetails prod = new ProductDetails(product.getProductName(), product.getShortDescription(), product.getDetailedDescription(),
					product.getCategory(), product.getStartingPrice(), bidEndDate.toString()) ;
			
			ProductBidSummary summary = new ProductBidSummary(prod, bidSummary) ; 
			response.setMessage("Fetched Bid Details for Product : "+productId);
			response.setResponse(summary);
			
		}catch(EAutionGenericException eaexe) {
			log.error("Error Occured While deleting product");
			response.setMessage("Error Occured While deleting product");
			response.setResponse(eaexe.getMessage());
		}
		
		return response ;
	}
		

	private Product mapProductEntity(ProductRequest productReq) {
		
		Product product = new Product(0, productReq.getProductName(), productReq.getShortDescription(), productReq.getDetailedDescription(),
				getProductCategory(productReq.getCategory()), productReq.getStartingPrice(), DateUtility.convertUtilToSqlDate(productReq.getBidEndDate()), 0) ;
		
		return product ;
	}
	
	private SellerInformation mapSellerEntity(ProductRequest productReq) {
		SellerInformation seller = new SellerInformation(0, productReq.getFirstName(), productReq.getLastName(), productReq.getAddress(),
				productReq.getCity(), productReq.getState(), productReq.getPin(), productReq.getPhone(), productReq.getEmail()) ;
		
		return seller ;
	}
	
	private String getProductCategory(String reqCategory) {
		log.debug("Category "+reqCategory);
		switch(reqCategory) {
		  case "Painting":
			  return ProductCategory.Painting.name();
		  case "Sculptor":
			  return ProductCategory.Sculptor.name();
		  case "Ornament":
			  return ProductCategory.Ornament.name();
		  default:
		    return null ;
		}
	}
	
	private boolean validateBidDetails(int productId) {
		boolean isValid = false ;
		Product prd = getProductDetails(productId) ;
		Timestamp bidEndDate = prd.getBidEndDate() ;
		if(DateUtility.isBidDateValid(bidEndDate)) {
			List<ProductBid> list = bidRepo.findAllBids(productId) ;
			if(!list.isEmpty() && list.size() > 0) {
				isValid = true ;
			}else {
				isValid = false ;
			}
		}else {
			isValid = false ;
		}
		
		return isValid ;
	}
	

	private Product getProductDetails(int productId) {
		
		Product prduct = null ;
		try {
			Optional<Product> prd = productRepo.findById(productId) ;
			prduct = prd.get();
			
		}catch(NoSuchElementException  exe) {
			throw new EAutionGenericException("Product Not Found.",exe) ;
		}catch(EAutionGenericException exe) {
			throw new EAutionGenericException("Error Occured while Getting Product.",exe) ;
		}
		return prduct ;
	}
}
