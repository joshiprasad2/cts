package org.eauction.repos;


import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

import javax.activation.DataSource;
import javax.persistence.EntityManager;

import org.eauction.entity.ProductBid;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductBidRepositoryTest {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate ;
	
	@Autowired
	private EntityManager entityManager; 
	
	@Autowired
	private ProductBidRepository bidRepository ;
	
	@Test
	public void injectComponentAreNotNull() {
		assertNotNull(dataSource);
		assertNotNull(jdbcTemplate);
		assertNotNull(entityManager);
		assertNotNull(bidRepository);
	}
	
	@Test
	public void testProductBidRepo() {
		ProductBid bid = new ProductBid() ;
			bid.setProductId(1);
			bid.setBidAmount(45000.00);
			bid.setEmail("zyz@gmail.com");
			bid.setPhone("1234567890");
			bid.setAddress("adsf");
			bid.setCity("Pune");
			bid.setPin(411045);
			bid.setState("MH");
			
		bidRepository.save(bid)	;
		
		verify(bidRepository).findAllBids(1);
		verify(bidRepository).findBidsByEmail("zyz@gmail.com",1);
	}
}
