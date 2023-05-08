package org.eauction.repos;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

import javax.activation.DataSource;
import javax.persistence.EntityManager;

import org.eauction.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate ;
	
	@Autowired
	private EntityManager entityManager; 
	
	@Autowired
	private UserRepository userRepository ;
	
	@Test
	public void injectComponentAreNotNull() {
		assertNotNull(dataSource);
		assertNotNull(jdbcTemplate);
		assertNotNull(entityManager);
		assertNotNull(userRepository);
	}
	
	@Test
	public void testUserRepo() {
		UserEntity user = new UserEntity() ;
		user.setUserID(111);
		user.setUserName("xxx");
		user.setPassword("42c5e235d4d55");
		user.setUserToken("asdfa5asdfa:ad5fads5f:a2552asdf");
		
		userRepository.save(user) ;
		
		verify(userRepository).findById(111) ;
		verify(userRepository).findByUsername("xxx") ;
	}
	
}
