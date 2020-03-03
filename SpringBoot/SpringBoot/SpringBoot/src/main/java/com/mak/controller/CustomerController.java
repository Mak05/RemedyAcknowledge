package com.mak.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mak.model.Customer;
import com.mak.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@RequestMapping("/home")
	public ModelAndView showHome(){
		
		return new ModelAndView("home");

	}
	@RequestMapping("/add")
	public ModelAndView addCustomer(@ModelAttribute("customer") Customer customer){
		
		return new ModelAndView("add-customer");
	}
	
	@RequestMapping("/save")
	public ModelAndView saveCustomer(@Validated @ModelAttribute("customer") Customer customer,BindingResult result){
		
		System.out.println("result.hasErrors()" +result.hasErrors());
		if(result.hasErrors()){
			return new ModelAndView("add-customer");
		}
		customerService.saveCustomer(customer);
		return new ModelAndView("redirect:show");
	}
	@RequestMapping("/show")
	public ModelAndView showCustomer(){
		
		List<Customer> customerlist=(List<Customer>) customerService.showCustomer();

		return new ModelAndView("show-customer","customerlist",customerlist);
	}
}
