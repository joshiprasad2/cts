package org.eauction.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Builder;

@Builder
@Entity
@Table(name = "PRODUCT_BID_SUMMARY")
public class ProductBid {

	@Id
	@GeneratedValue
	private int bidId ;
	
	private String firstName;
	private String lastName;
	private String address; 
	private String city ;
	private String state ;
	private int pin ; 
	private String phone ;
	
	@NotNull(message = "Email Cannot be null")
	@Email(message = "Invalid Email Address")
	@Column(name = "EMAIL",unique = true)
	private String email ; 
	
	private int productId; 
	private double bidAmount ;
	public int getBidId() {
		return bidId;
	}
	public void setBidId(int bidId) {
		this.bidId = bidId;
	}
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
	public ProductBid(int bidId, String firstName, String lastName, String address, String city, String state, int pin,
			String phone,
			@NotNull(message = "Email Cannot be null") @Email(message = "Invalid Email Address") String email,
			int productId, double bidAmount) {
		super();
		this.bidId = bidId;
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
	public ProductBid() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
