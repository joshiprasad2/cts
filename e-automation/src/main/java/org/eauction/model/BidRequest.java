package org.eauction.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Builder;

@Builder
public class BidRequest {

	@NotNull(message = "First Name Cannot Be Null")
	@Size(min = 5, max = 30, message = "First Name must be between 5 and 30 characters")
	private String firstName;
	
	@NotNull(message = "Last Name Cannot Be Null")
	@Size(min = 3, max = 25, message = "Last Name must be between 3 and 25 characters")
	private String lastName;
	
	private String address; 
	private String city ;
	private String state ;
	private int pin ; 

	@NotNull(message = "Phone Cannot be null")
	@Pattern(regexp = "^\\d{10}$")
	private String phone ;
	
	@NotNull(message = "Email Cannot be null")
	@Email(message = "Invalid Email Address")
	private String email ; 
	
	private int productId; 
	private double bidAmount ;
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
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public double getBidAmount() {
		return bidAmount;
	}
	public void setBidAmount(double bidAmount) {
		this.bidAmount = bidAmount;
	}
	public BidRequest(
			@NotNull(message = "First Name Cannot Be Null") @Size(min = 5, max = 30, message = "First Name must be between 5 and 30 characters") String firstName,
			@NotNull(message = "Last Name Cannot Be Null") @Size(min = 3, max = 25, message = "Last Name must be between 3 and 25 characters") String lastName,
			String address, String city, String state, int pin,
			@NotNull(message = "Phone Cannot be null") @Pattern(regexp = "^\\d{10}$") String phone,
			@NotNull(message = "Email Cannot be null") @Email(message = "Invalid Email Address") String email,
			int productId, double bidAmount) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.pin = pin;
		this.phone = phone;
		this.email = email;
		this.productId = productId;
		this.bidAmount = bidAmount;
	}
	public BidRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "BidRequest [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", city="
				+ city + ", state=" + state + ", pin=" + pin + ", phone=" + phone + ", email=" + email + ", productId="
				+ productId + ", bidAmount=" + bidAmount + "]";
	}

	
	
}
