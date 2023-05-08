package org.eauction.service;

import java.sql.Timestamp;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.eauction.entity.Product;
import org.eauction.entity.ProductBid;
import org.eauction.exceptions.EAutionGenericException;
import org.eauction.model.AuctionResponse;
import org.eauction.model.BidRequest;
import org.eauction.repos.ProductBidRepository;
import org.eauction.repos.ProductRepository;
import org.eauction.utils.DateUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EAuctionService {

	private static final Logger log = LoggerFactory.getLogger(EAuctionService.class) ;

	@Autowired
	private ProductRepository productRepo ;
	
	@Autowired
	ProductBidRepository bidRepo;
	
	public AuctionResponse persistBidData(BidRequest bidRequest) {
		log.debug("bidRequest to place bid :"+ bidRequest);
		AuctionResponse response = new AuctionResponse() ;
		int bidId = 0 ;
		try {
			ProductBid prdBids = new ProductBid(0, bidRequest.getFirstName(), bidRequest.getLastName(),
					bidRequest.getAddress(), bidRequest.getCity(), bidRequest.getState(), bidRequest.getPin(), bidRequest.getPhone(),
					bidRequest.getEmail(), bidRequest.getProductId(), bidRequest.getBidAmount()) ;
			
			prdBids = bidRepo.save(prdBids) ;
			bidId = prdBids.getBidId() ;
			log.debug("bidRequest bidId :"+ bidId);
			response.setMessage("Bid Placed Successfully. ") ;
			response.setResponse(bidId) ;
			
		}catch(EAutionGenericException exe) {
			log.error("Error Occured while placing the Bid Order.");
			response.setMessage("Error Occured while placing the Bid Order. | "+exe.getMessage()) ;
			response.setResponse(bidId) ;
//			throw new EAutionGenericException(exe.getMessage(),exe) ;
		}
		return response ;
	}
	
	public AuctionResponse updateBidData(int productId,String buyerEmail, double bidAmount) {
		log.debug("bidRequest to update bid :"+ buyerEmail);
		AuctionResponse response = new AuctionResponse() ;
		try {
			
			Optional<ProductBid> existingBid = bidRepo.findBidsByEmail(buyerEmail,productId) ;
			ProductBid bidEntity = existingBid.get() ;
			
			bidEntity.setBidAmount(bidAmount);
			ProductBid optBid = bidRepo.save(bidEntity) ;
			log.info("Bid Updated successfully.");
			response.setMessage("Bid Amount Updated for Product : "+optBid.getProductId()) ;
			response.setResponse(optBid.getBidAmount()) ;
			
		}catch(NoSuchElementException eleexe) {
			log.error("No Bid Detailes Found for the buyerEmail : "+buyerEmail);
			response.setMessage("No Bid Detailes Found for the BidId : \"+bidId | "+eleexe.getMessage()) ;
			response.setResponse(buyerEmail) ;
//			throw new EAutionGenericException(exe.getMessage(),exe) ;
		}
		catch(EAutionGenericException exe) {
			log.error("Error Occured while updating the Bid Order.");
			response.setMessage("Error Occured while updating the Bid Order. | "+exe.getMessage()) ;
			response.setResponse(buyerEmail) ;
//			throw new EAutionGenericException(exe.getMessage(),exe) ;
		}
		return response ;
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

	public boolean validateBidDetails(BidRequest bidRequest) {
		boolean isValid = false ;
		try {
			Product prd = getProductDetails(bidRequest.getProductId()) ;
			Timestamp bidEndDate = prd.getBidEndDate() ;
			if(DateUtility.isBidDateValid(bidEndDate)) {
				isValid = true ;
			}else {
				isValid = false ;
			}
		}catch(NoSuchElementException  exe) {
			isValid = false ;
			throw new EAutionGenericException(exe.getMessage(),exe) ;
		}catch(EAutionGenericException exe) {
			isValid = false ;
			throw new EAutionGenericException("Error Occured while Validating.",exe) ;
		}
		return isValid ;
	}

	public boolean validateBidDetailsByEmail(int productId, String email) {
		boolean isValid = false ;
		try {
			Optional<ProductBid> existingBid = bidRepo.findBidsByEmail(email,productId) ;
			ProductBid bidEntity = existingBid.get() ;
			
			Product prd = getProductDetails(bidEntity.getProductId()) ;
			Timestamp bidEndDate = prd.getBidEndDate() ;
			
			if(DateUtility.isBidDateValid(bidEndDate)) {
				isValid = true ;
			}else {
				isValid = false ;
			}
		}catch(NoSuchElementException  exe) {
			isValid = false ;
			throw new EAutionGenericException(exe.getMessage(),exe) ;
		}catch(EAutionGenericException exe) {
			isValid = false ;
			throw new EAutionGenericException("Error Occured while Validating.",exe) ;
		}
		return isValid ;
	}

}
