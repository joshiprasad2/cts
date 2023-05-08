package org.eauction.repos;

import org.eauction.entity.SellerInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<SellerInformation, Integer>{

}
