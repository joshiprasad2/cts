package org.eauction.repos;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

import javax.activation.DataSource;
import javax.persistence.EntityManager;

import org.eauction.entity.SellerInformation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SellerRepositoryTest {

	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate ;
	
	@Autowired
	private EntityManager entityManager; 
	
	@Autowired
	private SellerRepository repository ;
	
	@Test
	public void injectComponentAreNotNull() {
		assertNotNull(dataSource);
		assertNotNull(jdbcTemplate);
		assertNotNull(entityManager);
		assertNotNull(repository);
	}
	
	@Test
	public void testSellerRepo() {
		SellerInformation seller = new SellerInformation() ;
		seller.setAddress("sdf");
		seller.setSellerId(123) ;
		seller.setCity("Pune");
		seller.setEmail("sell@gmail.com");
		seller.setFirstName("firs");
		seller.setLastName("last");
		seller.setPhone("1234567890");
		seller.setPin(411052);
		seller.setState("state");
		
		repository.save(seller);
		
		verify(repository).findById(123) ;
	}
	
}
