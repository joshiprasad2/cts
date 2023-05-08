package org.eauction.axon.events.handlers;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.eauction.axon.events.ProductCreatedEvent;
import org.eauction.model.ProductRequest;
import org.eauction.service.SellerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("Products")
public class ProductEventsHandler {
	
	@Autowired
	private SellerService sellerService ;

	@EventHandler
	public void on(ProductCreatedEvent productCreatedEvent) {
		ProductRequest req = ProductRequest.builder().build();
		BeanUtils.copyProperties(productCreatedEvent, req);
		sellerService.addNewProduct(req);	
	}
}
