package com.books.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.books.model.BillingAddress;
import com.books.model.Customer;
import com.books.model.ShippingAddress;
import com.books.model.Users;
import com.books.services.CustomerServices;

@Controller
public class RegistrationController {
	@Autowired
	private CustomerServices customerServices;
	
	public CustomerServices getCustomerServices() {
		return customerServices;
	}

	public void setCustomerServices(CustomerServices customerServices) {
		this.customerServices = customerServices;
	}

	@RequestMapping("/customer/registration")
	public ModelAndView getRegistrationForm(){
	Customer customer = new Customer();
	Users users = new Users();
	BillingAddress billingAddress = new BillingAddress();
	ShippingAddress shippingAddress = new ShippingAddress();
	customer.setUsers(users);
	customer.setBillingAddress(billingAddress);
	customer.setShippingAddress(shippingAddress);
	return new ModelAndView("registerCustomer","customer",customer);
}
		
	@RequestMapping(value="/customer/registration",method=RequestMethod.POST)
	public String registerCustomer(@Valid @ModelAttribute(value="customer")Customer customer, Model model, BindingResult result){
		
		if(result.hasErrors())
			return "registerCustomer";
		List<Customer> customersList = customerServices.getAllCustomers();
		for(Customer c:customersList){
		
			if(c.getCustomerEmail().equals(customer.getCustomerEmail())){
				model.addAttribute("DuplicateEmail","Email already exists");
				return "registerCustomer";
			}
			if(c.getUsers().getUsername().equals(customer.getUsers().getUsername()))
			{
				model.addAttribute("DuplicateUname","Username already exists");
				return"registerCustomer";
			}
		}
		
		customerServices.addCustomer(customer);
		model.addAttribute("registrationSuccess","Registered Successfully. Login using Username & Password");
		return "login";
	}
}
