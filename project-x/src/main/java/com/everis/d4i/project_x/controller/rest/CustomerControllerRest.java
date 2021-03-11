package com.everis.d4i.project_x.controller.rest;

import com.everis.d4i.project_x.controller.rest.model.D4iPageRest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.EntityModel;

import com.everis.d4i.project_x.controller.rest.model.SalesResponse;
import com.everis.d4i.project_x.controller.rest.model.CustomerRest;
import com.everis.d4i.project_x.exception.SalesException;

public interface CustomerControllerRest {

    SalesResponse<D4iPageRest <CustomerRest>> getAllCustomers(int page, int size, Pageable pageable,
                                                              PagedResourcesAssembler<CustomerRest> assembler) throws SalesException;

    SalesResponse<CustomerRest> createCustomer(CustomerRest customer) throws SalesException;

    SalesResponse<CustomerRest> getCustomerById(Long id) throws SalesException;

    SalesResponse<CustomerRest> updateCustomer(CustomerRest customer) throws SalesException;

    void deleteCustomer(Long id) throws SalesException;
}
