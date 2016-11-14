package com.books.services;

import java.util.List;

import com.books.model.Customer;

public interface CustomerServices {
void addCustomer(Customer customer);
List<Customer> getAllCustomers();
Customer getCustomerByUsername(String username);
}
