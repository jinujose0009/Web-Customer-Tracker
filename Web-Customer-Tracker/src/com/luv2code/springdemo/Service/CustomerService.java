package com.luv2code.springdemo.Service;

import java.util.List;

import com.luv2code.springdemo.entity.Customer;

public interface CustomerService {
	
	public List<Customer> getCustomers();

	public void saveCustomer(Customer thecustomer);

	public Customer getCustomet(int custid);

	public void deleteCustomer(int custid);

	public List<Customer> searchCustomers(String theSearchName);

}
