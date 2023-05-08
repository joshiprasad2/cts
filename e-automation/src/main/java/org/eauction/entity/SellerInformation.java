package org.eauction.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "SELLER_INFORMATION")
public class SellerInformation {

	@Id
	@GeneratedValue
	@Column(name = "SELLER_ID",unique = true)
	private int sellerId;
	
	@NotNull(message = "First Name Cannot Be Null")
	@Size(min = 5, max = 30, message = "First Name must be between 5 and 30 characters")
	@Column(name = "FIRST_NAME",unique = true)
	private String firstName;
	
	@NotNull(message = "Last Name Cannot Be Null")
	@Size(min = 3, max = 25, message = "Last Name must be between 3 and 25 characters")
	@Column(name = "LAST_NAME",unique = true)
	private String lastName ;
	
	private String address ;
	private String city  ;
	private String state; 
	private int pin ;
	
	@NotNull(message = "Phone Cannot be null")
	@Pattern(regexp = "^\\d{10}$")
	@Column(name = "PHONE_NO",unique = true)
	private String phone ;
	
	@NotNull(message = "Email Cannot be null")
	@Email(message = "Invalid Email Address")
	@Column(name = "EMAIL",unique = true)
	private String email ;

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
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

	public SellerInformation(int sellerId,
			@NotNull(message = "First Name Cannot Be Null") @Size(min = 5, max = 30, message = "First Name must be between 5 and 30 characters") String firstName,
			@NotNull(message = "Last Name Cannot Be Null") @Size(min = 3, max = 25, message = "Last Name must be between 3 and 25 characters") String lastName,
			String address, String city, String state, int pin,
			@NotNull(message = "Phone Cannot be null") @Pattern(regexp = "^\\d{10}$") String phone,
			@NotNull(message = "Email Cannot be null") @Email(message = "Invalid Email Address") String email) {
		super();
		this.sellerId = sellerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.pin = pin;
		this.phone = phone;
		this.email = email;
	}

	public SellerInformation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
