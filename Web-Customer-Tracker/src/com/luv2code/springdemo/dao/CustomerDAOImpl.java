package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the customer service
	
	@Autowired
	private SessionFactory sessionFactory;
			
	@Override
	public List<Customer> getCustomers() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// create a query.. sorted by firstname
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer order by firstName", Customer.class);
		
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
				
		// return the results		
		return customers;
	}

	@Override
	public void saveCustomer(Customer thecustomer) {
		// get current hibernate session
		Session currSession=sessionFactory.getCurrentSession();
		// save the customer ... finally LOL
		currSession.saveOrUpdate(thecustomer);
		
	}

	@Override
	public Customer getCustomer(int custid) {
		//get current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		//get customer data uisng primary key
		Customer thecustomer=currentSession.get(Customer.class, custid);
		return thecustomer;
	}

	@Override
	public void deleteCustomer(int custid) {
		Session currentSession=sessionFactory.getCurrentSession();
		Query thequery=
				currentSession.createQuery("delete from Customer where id=:customerid");
				thequery.setParameter("customerid", custid);
		thequery.executeUpdate();
							

	}

	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		  // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        
		  Query theQuery = null;
	        
	        //
	        // only search by name if theSearchName is not empty
	        //
	        if (theSearchName != null && theSearchName.trim().length() > 0) {

	            // search for firstName or lastName ... case insensitive
	            theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
	            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

	        }
	        else {
	            // theSearchName is empty ... so just get all customers
	            theQuery =currentSession.createQuery("from Customer", Customer.class);            
	        }
	        
	        // execute query and get result list
	        List<Customer> customers = theQuery.getResultList();
	                
	        // return the results        
	        return customers;
	}

}






