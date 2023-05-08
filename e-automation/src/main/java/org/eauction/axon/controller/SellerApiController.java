package org.eauction.axon.controller;

import javax.validation.Valid;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.eauction.axon.seller.commands.ProductCommand;
import org.eauction.axon.seller.query.GetProductBids;
import org.eauction.model.AuctionResponse;
import org.eauction.model.ProductRequest;
import org.eauction.service.SellerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/e-auction/api/v1/seller")
public class SellerApiController {

	@Autowired
	private SellerService sellerService ;
	
	
	private QueryGateway queryGateway ;
	
	public SellerApiController(CommandGateway commandGateway,QueryGateway queryGateway) {
		this.queryGateway = queryGateway ;
	}

	
	@Operation(summary = "Add New Products.",
			description = "Add New Products.",
			tags = "Products",
			responses = {
				@ApiResponse(
					responseCode = "200",
					description = "Ok",
					content = {
						@Content(
							mediaType = "application/json"
						)}
					)
			},
			requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
					content = {
							@Content(
								mediaType = "application/json"	
						)},
					description = "Add New Product", required = true))
	@PostMapping("/add-product")
	public ResponseEntity<AuctionResponse> addNewProduct(@RequestBody @Valid ProductRequest productReq,
			BindingResult result) {
		log.info(""+productReq);
		if(result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null) ;
		}else {
			ProductCommand product = ProductCommand.builder().build() ;
			BeanUtils.copyProperties(productReq, product);
//			AutionResponse response =	commandGateway.sendAndWait(product);
			AuctionResponse response = sellerService.addNewProduct(productReq) ;
			return ResponseEntity.ok().body(response);
		}
		
	}
	
	@DeleteMapping("/delete/{productId}")
	@Operation(summary = "Remove Product",
			description = "Remove Product",
			tags = "Products",
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Ok"
							)
			})
	public ResponseEntity<AuctionResponse> deleteProduct(@PathVariable("productId") int productId) {
		AuctionResponse response = sellerService.deleteProduct(productId) ;
		
		return ResponseEntity.ok().body(response);
	}
	
	
	@Operation(summary = "Get List for All placed Bid",
			description = "Get List for All placed Bid",
			tags = "ProductBid",
			parameters = {
					@Parameter(name = "productId",required = false,description = "Product Id to get list of Bids.")
			},
			responses = {
				@ApiResponse(
					responseCode = "200",
					description = "Ok",
					content = {
						@Content(
							mediaType = "application/json"
						)}
					)
			})
	@GetMapping("/show-bids/{productId}")
	public ResponseEntity<AuctionResponse> listAllProductBids(@PathVariable("productId") int productId) {
		log.info("Received Request to fetch all bids placed for product-id : {}",productId);
		GetProductBids getBidQuery = new GetProductBids() ;
		getBidQuery.setProductId(productId) ;
		AuctionResponse response = queryGateway.query(getBidQuery, ResponseTypes.instanceOf(AuctionResponse.class)).join() ;
//		AutionResponse response = sellerService.listAllProductBids(productId) ;
		log.info("Response from Query Handler : {}",response );
		return ResponseEntity.ok().body(response);
	}
}
