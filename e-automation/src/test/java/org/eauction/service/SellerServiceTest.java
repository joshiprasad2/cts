package org.eauction.service;


import java.util.ArrayList;
import java.util.List;

import org.eauction.entity.ProductBid;
import org.eauction.model.AuctionResponse;
import org.eauction.model.ProductBidSummary;
import org.eauction.model.ProductRequest;
import org.eauction.repos.ProductBidRepository;
import org.eauction.repos.ProductRepository;
import org.eauction.repos.SellerRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

@RunWith(MockitoJUnitRunner.class)
public class SellerServiceTest {

	@InjectMocks
	private SellerService sellerService ;
	
	@Mock
	private ProductRepository productRepo;
	
	@Mock
	private SellerRepository sellerRepo ;
	
	@Mock
	private ProductBidRepository bidRepo ;
	
	@Test
	public void testAddNewProduct() {
		ProductRequest prdRequest = ProductRequest.builder()
				.firstName("firstname")
				.lastName("lastname")
				.address("asdf")
				.city("pune")
				.state("sdf")
				.pin(41253)
				.phone("123567890")
				.email("xxx@gmailc.com")
				.bidEndDate("2023-05-15 17:10:00")
				.category("Sculptor")
				.productName("Wall Sculptor")
				.startingPrice(40000.0)
				.build() ;
		
		AuctionResponse response = new AuctionResponse();
		
		ProductBid prdBids = ProductBid.builder().build() ;
		prdBids.setBidId(101);
		BeanUtils.copyProperties(prdRequest, prdBids);
		Mockito.when(bidRepo.save(prdBids)).thenReturn(prdBids) ;
		response.setResponse(prdBids.getBidId());
		
		response = sellerService.addNewProduct(prdRequest) ;		
		Assertions.assertNotNull(response);
	
	}
	
	@Test
	public void testListAllBids() {
		
		AuctionResponse response = new AuctionResponse();
		List<ProductBid> list = new ArrayList<>();
		
		ProductBid prdBids = ProductBid.builder().build() ;
		prdBids.setBidId(101);
		prdBids.setAddress("Address");
		prdBids.setBidAmount(580000.0);
		prdBids.setBidId(101);
		prdBids.setCity("Pune");
		prdBids.setEmail("xxx@gmail.com");
		prdBids.setFirstName("First Name");
		prdBids.setProductId(10);
		
		ProductBid prdBids2 = ProductBid.builder().build() ;
		prdBids2.setBidId(101);
		prdBids2.setAddress("Address");
		prdBids2.setBidAmount(780000.0);
		prdBids2.setBidId(102);
		prdBids2.setCity("Pune");
		prdBids2.setEmail("xxx_yyy@gmail.com");
		prdBids2.setFirstName("First Name");
		prdBids2.setProductId(10);
		
		list.add(prdBids);
		list.add(prdBids2) ;
		
		Mockito.when(bidRepo.findAllBids(10)).thenReturn(list) ;
		
		response = sellerService.listAllProductBids(10);	
		
		Assertions.assertNotNull(response);
		
		ProductBidSummary summary = (ProductBidSummary)response.getResponse() ;
		Assertions.assertEquals(Double.valueOf(780000.0),Double.valueOf(summary.getBidDetails().get(1).getBidAmount()));
	}
}
