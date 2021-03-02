package com.everis.d4i.project_x.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.EntityModel;

import com.everis.d4i.project_x.controller.rest.model.CustomerRest;
import com.everis.d4i.project_x.exception.SalesException;

public interface CustomerService {

    PagedModel<EntityModel<CustomerRest>> getAllCustomers(Pageable pageable, PagedResourcesAssembler<CustomerRest> assembler)
	    throws SalesException;

    CustomerRest createCustomer(CustomerRest Customer) throws SalesException;

    CustomerRest getCustomerById(Long id) throws SalesException;

    CustomerRest updateCustomer(CustomerRest CustomerDetails) throws SalesException;

    void deleteCustomer(Long id) throws SalesException;

}
