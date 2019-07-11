package com.luv2code.springdemo.controller;

import java.util.List;

import javax.swing.text.DefaultEditorKit.CutAction;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.Service.CustomerService;
import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// need to inject the customer service
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/list")
	public String listCustomers(Model theModel) {
		
		// get customers from the dao
		List<Customer> theCustomers = customerService.getCustomers();
				
		// add the customers to the model
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}
	
	
	@GetMapping("/showFormForAdd")
	public String showCustomerAddform(Model themodel){
		Customer customer=new Customer();
		themodel.addAttribute("customer",customer);
		return "customer-registration";
	}
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer thecustomer){
		
		//Save the customer using our service
		customerService.saveCustomer(thecustomer);
		
		return "redirect:/customer/list";
	}
	@GetMapping("/showFormforEdit")
	public String shoeFormforEdit(@RequestParam("cusomerid") int custid,Model themodel){
		//get the customer from service
		Customer thecustomer=customerService.getCustomet(custid);
		//set the customer as a model attribute to pre-populate the form
		themodel.addAttribute("customer",thecustomer);
		
		//sent over to our form 
		return "customer-registration";
	}
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerid") int custid,Model themodel){
		//get the customer from service
		customerService.deleteCustomer(custid);
		
		return "redirect:/customer/list";
	}
	  @GetMapping("/search")
	    public String searchCustomers(@RequestParam("theSearchName") String theSearchName,
	                                    Model theModel) {

	        // search customers from the service
	        List<Customer> theCustomers = customerService.searchCustomers(theSearchName);
	                
	        // add the customers to the model
	        theModel.addAttribute("customers", theCustomers);

	        return "list-customers";        
	    }
}


