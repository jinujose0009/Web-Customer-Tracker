package com.luv2code.springdemo.dao;

import java.util.List;

import com.luv2code.springdemo.entity.Customer;

public interface CustomerDAO {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer thecustomer);

	public Customer getCustomer(int custid);

	public void deleteCustomer(int custid);

	public List<Customer> searchCustomers(String theSearchName);
	
}
