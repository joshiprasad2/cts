package org.eauction.service;

import java.util.Optional;

import org.eauction.entity.ProductBid;
import org.eauction.model.AuctionResponse;
import org.eauction.model.BidRequest;
import org.eauction.repos.ProductBidRepository;
import org.eauction.repos.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

@RunWith(MockitoJUnitRunner.class)
public class EAuctionServiceTest {
	
	@InjectMocks
	private EAuctionService auctionService ;
	
	@Mock
	private ProductRepository productRepo ;
	
	@Mock
	private ProductBidRepository bidRepo;
	
	@Before
	public void setup() {
		MockitoAnnotations.openMocks(this) ;
	}
	
	@Test
	public void testPersistBidData() {

		BidRequest bidRequest = BidRequest.builder()
				.firstName("firstname")
				.lastName("lastname")
				.address("asdf")
				.city("pune")
				.state("sdf")
				.pin(41253)
				.phone("123567890")
				.email("xxx@gmailc.com")
				.productId(10)
				.bidAmount(45000.0).build() ;
		
		AuctionResponse response = new AuctionResponse();
		
		ProductBid prdBids = ProductBid.builder().build() ;

		BeanUtils.copyProperties(bidRequest, prdBids);
		prdBids.setBidId(101);
		
		Mockito.when(bidRepo.save(Mockito.any(ProductBid.class))).thenReturn(prdBids) ;
		response.setResponse(prdBids.getBidId());
		
		response = auctionService.persistBidData(bidRequest) ;
		
		Assertions.assertNotNull(response);
		
	}

	@Test
	public void testUpdateBidData() {

		BidRequest bidRequest = BidRequest.builder()
				.firstName("firstname")
				.lastName("lastname")
				.address("asdf")
				.city("pune")
				.state("sdf")
				.pin(41253)
				.phone("123567890")
				.email("xxx@gmailc.com")
				.productId(10)
				.bidAmount(45000.0).build() ;
		
		AuctionResponse response = new AuctionResponse();
		
		ProductBid prdBids = ProductBid.builder().build() ;
		prdBids.setBidId(101);
		BeanUtils.copyProperties(bidRequest, prdBids);
		
		Optional<ProductBid> optProductBid = Optional.of(prdBids) ;
		
		Mockito.when(bidRepo.findBidsByEmail("xxx@gmail.com",10)).thenReturn(optProductBid) ;
		optProductBid.get().setBidAmount(580000.0);
		
		Mockito.when(bidRepo.save(prdBids)).thenReturn(prdBids) ;
		
		response.setResponse(prdBids.getBidId());
		
		response = auctionService.updateBidData(10, "", 52000.0);
		
		Assertions.assertNotNull(response);
//		Assertions.assertEquals(580000.0, response.getResponse());
	}
	

}
