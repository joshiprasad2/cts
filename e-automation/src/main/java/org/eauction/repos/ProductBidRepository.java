package org.eauction.repos;

import java.util.List;
import java.util.Optional;

import org.eauction.entity.ProductBid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductBidRepository extends JpaRepository<ProductBid, Integer>{

	@Query("SELECT b FROM ProductBid b WHERE b.productId = :productId")
	public List<ProductBid> findAllBids(@Param("productId") int productId);
	
	@Query("SELECT b FROM ProductBid b WHERE b.email = :email and b.productId = :productId")
	public Optional<ProductBid> findBidsByEmail(@Param("email") String email,@Param("productId") int productId);
	
}
