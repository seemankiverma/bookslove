package com.books.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="b_customer")
public class Customer implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int customerId;
@NotEmpty(message="Customer Name can not be empty")
private String customerName;
@NotEmpty(message="Customer Email can not be empty")
@Column(unique=true)
private String customerEmail;
private String customerPhone;

@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
@JoinColumn(name="cartid")
@JsonIgnore
private Cart cart;

@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
@JoinColumn(name="usersid")
private Users users;

@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
@JoinColumn(name="billingaddressid")
private BillingAddress billingAddress;

@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
@JoinColumn(name="shippingaddressid")
private ShippingAddress shippingAddress;

public Cart getCart() {
	return cart;
}
public void setCart(Cart cart) {
	this.cart = cart;
}

public int getCustomerId() {
	return customerId;
}
public void setCustomerId(int customerId) {
	this.customerId = customerId;
}
public String getCustomerName() {
	return customerName;
}
public void setCustomerName(String customerName) {
	this.customerName = customerName;
}
public String getCustomerEmail() {
	return customerEmail;
}
public void setCustomerEmail(String customerEmail) {
	this.customerEmail = customerEmail;
}
public String getCustomerPhone() {
	return customerPhone;
}
public void setCustomerPhone(String customerPhone) {
	this.customerPhone = customerPhone;
}
public Users getUsers() {
	return users;
}
public void setUsers(Users users) {
	this.users = users;
}
public BillingAddress getBillingAddress() {
	return billingAddress;
}
public void setBillingAddress(BillingAddress billingAddress) {
	this.billingAddress = billingAddress;
}
public ShippingAddress getShippingAddress() {
	return shippingAddress;
}
public void setShippingAddress(ShippingAddress shippingAddress) {
	this.shippingAddress = shippingAddress;
}
}
