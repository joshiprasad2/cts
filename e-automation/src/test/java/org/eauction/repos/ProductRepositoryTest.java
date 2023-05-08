package org.eauction.repos;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.eauction.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate ;
	
	@Autowired
	private EntityManager entityManager; 
	
	@Autowired
	private ProductRepository productRepo ;
	
	@Test
	public void injectComponentAreNotNull() {
		
		assertThat(dataSource).isNotNull();
		assertThat(jdbcTemplate).isNotNull();
		assertThat(entityManager).isNotNull();
		assertThat(productRepo).isNotNull();
	}
	
	@Test
	public void testProductRepo() {
		Product product = new Product() ;
		product.setProductId(1);
		product.setCategory("Sculpter");
		product.setProductName("Wall Sculpters");
		product.setShortDescription("Wall Sculpters");
		product.setStartingPrice(51000);
		product.setDetailedDescription("Wall Sculpters");
		product.setSellerId(2);
		
		productRepo.save(product) ;
		
		verify(productRepo).findById(1) ;
	}
}
