package com.books.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.books.model.Authorities;
import com.books.model.Cart;
import com.books.model.Customer;
import com.books.model.Users;

@Repository
public class CustomerDaoImpl implements CustomerDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addCustomer(Customer customer) {
	
		Session session = sessionFactory.openSession();
		customer.getUsers().setEnabled(true);
		
		Authorities authorities = new Authorities(); //Transient heap memory only
		String username = customer.getUsers().getUsername();
		authorities.setUsername(username);
		authorities.setAuthorities("ROLE_USER");
		
		Cart cart = new Cart();//new cart
		//It is to set CartId for Customer table
		customer.setCart(cart);//set the cart to the customer
		//if we omit this statement, then it will insert null for customerid in cart
		//to set the customerid in cart table
		cart.setCustomer(customer); 

		session.save(customer);
		session.save(authorities); // persistent(store in database)
		session.flush();
		session.close();
	}

	
	public List<Customer> getAllCustomers() {
		Session session=sessionFactory.openSession();
		List<Customer> customerList = session.createQuery("from Customer").list();
		return customerList;
	}

	public Customer getCustomerByUsername(String username) {
		Session session=sessionFactory.openSession();
		//using username - get users details
		Query query=session.createQuery("from Users where username=?");
		query.setString(0, username);
		Users users=(Users)query.uniqueResult();
		
		//From users get Customer details
		
		Customer customer=users.getCustomer();
		return customer;			
	}
}
