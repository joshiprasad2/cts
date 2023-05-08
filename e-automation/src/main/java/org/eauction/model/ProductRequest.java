package org.eauction.model;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.ToString;

@ToString
@Builder
public class ProductRequest {

	
	@NotNull(message = "First Name Cannot Be Null")
	@Size(min = 5, max = 30, message = "First Name must be between 5 and 30 characters")
	private String firstName;
	
	@NotNull(message = "Last Name Cannot Be Null")
	@Size(min = 3, max = 25, message = "Last Name must be between 3 and 25 characters")
	private String lastName ;
	
	private String address ;
	private String city  ;
	private String state; 
	private int pin ;
	
	@NotNull(message = "Phone Cannot be null")
	@Pattern(regexp = "^\\d{10}$")
	private String phone ;
	
	@NotNull(message = "Email Cannot be null")
	@Email(message = "Invalid Email Address")
	private String email ;

	
	@Column(name="PRODUCT_NAME")
	private String productName ;
	
	@Column(name="SHORT_DESCRIPTION")
	private String shortDescription;
	
	@Column(name="DETAILED_DESCRIPTION")
	private String detailedDescription ;
	
	@Column(name="CATEGORY")
	private String category ;
	
	@Column(name="STARTING_PRICE")
	private double startingPrice ;

	@Column(name="BID_END_DATE")
	private String bidEndDate ;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getDetailedDescription() {
		return detailedDescription;
	}

	public void setDetailedDescription(String detailedDescription) {
		this.detailedDescription = detailedDescription;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getStartingPrice() {
		return startingPrice;
	}

	public void setStartingPrice(double startingPrice) {
		this.startingPrice = startingPrice;
	}

	public String getBidEndDate() {
		return bidEndDate;
	}

	public void setBidEndDate(String bidEndDate) {
		this.bidEndDate = bidEndDate;
	}


	
}
