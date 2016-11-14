package com.books.dao;

import java.util.List;

import com.books.model.Customer;

public interface CustomerDao {
void addCustomer(Customer customer);
List<Customer> getAllCustomers();
Customer getCustomerByUsername(String username); // ask again it is right or not??
}
