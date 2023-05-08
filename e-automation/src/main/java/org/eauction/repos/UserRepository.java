package org.eauction.repos;

import java.util.Optional;

import org.eauction.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	@Query("SELECT b FROM UserEntity b WHERE b.userName = :username ")
	public Optional<UserEntity> findByUsername(@Param("username") String username);
	
}
