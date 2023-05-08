package org.eauction.axon.controller;

import javax.validation.Valid;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.eauction.axon.seller.commands.PlaceProductBidCommand;
import org.eauction.axon.seller.commands.UpdateProductBidCommand;
import org.eauction.model.AuctionResponse;
import org.eauction.model.BidRequest;
import org.eauction.service.EAuctionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
@RequestMapping(value = "/e-auction/api/v1/buyer")
public class BuyerControllerAPI {

	private CommandGateway commandGateway ;
	
	@Autowired
	private EAuctionService buyerService ;
	
	public BuyerControllerAPI(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}
	

	@Operation(summary = "Place Bid For Products.",
				description = "Place Bid For Products.",
				tags = "ProductBid",
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
						description = "Place Bid", required = true))
	@PostMapping("/place-bid")
	public ResponseEntity<AuctionResponse> placeBid(@RequestBody @Valid BidRequest bidReq,
			BindingResult result) {
		log.info("Binding Result: "+result) ;
		AuctionResponse response = new AuctionResponse() ;
		
		if(result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}else {
			
			if(buyerService.validateBidDetails(bidReq)) {
				PlaceProductBidCommand bidCommand = PlaceProductBidCommand.builder().build() ;
				BeanUtils.copyProperties(bidReq, bidCommand);
				Object obj = commandGateway.sendAndWait(bidCommand) ;
				
				response.setMessage("Bid Placed Successfully") ;
				response.setResponse(obj) ;
				
				log.info("Response : "+response );
				return ResponseEntity.ok().body(response);	
			}else {
				response.setMessage("Bid Cannot Be placed. As Bid Is Expired") ;
				response.setResponse(null) ;
				
				return ResponseEntity.badRequest().body(response);
			}
			
		}
	}
	
	
	@Operation(summary = "Update Bid For Products.",
			description = "Update Bid For Products.",
			tags = "ProductBid",
			parameters = {
					@Parameter(name = "productId",required = true,description = "Product Id To Update"),
					@Parameter(name = "bidId",required = true,description = "Bid Id To Update"),
					@Parameter(name = "buyerEmailld",required = false,description = "Buyer Email Address to Update."),
					@Parameter(name = "newBidAmount",required = false,description = "newBidAmount To Update")
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
	@PutMapping("/update-bid/{productId}/{buyerEmailld}/{newBidAmount}")
	public ResponseEntity<AuctionResponse> updateBid(@PathVariable("productId") int productId,@PathVariable("bidId") int bidId,
				@PathVariable("buyerEmailld") String buyerEmailld,@PathVariable("newBidAmount") double newBidAmount) {
		AuctionResponse response = new AuctionResponse() ;
		
		if(productId > 0 && bidId > 0 && newBidAmount > 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}else {
			
			if(buyerService.validateBidDetailsByEmail(productId,buyerEmailld)) {
				UpdateProductBidCommand bidCommand = UpdateProductBidCommand.builder().build() ;
				bidCommand.setBidAmount(newBidAmount);
				bidCommand.setBidId(bidId) ;
				bidCommand.setEmail(buyerEmailld) ;
				bidCommand.setProductId(productId) ;
				
				Object obj = commandGateway.sendAndWait(bidCommand) ;
				
				response.setMessage("Bid Updated Successfully") ;
				response.setResponse(obj) ;
				
				log.info("Response : "+response );
				return ResponseEntity.ok().body(response);
			}else {
				response.setError(true);
				response.setMessage("Bid Cannot Be updated as bid end date is over.") ;
				response.setResponse(null) ;
				return ResponseEntity.badRequest().body(response);
			}
			
		}
		
	}
}
