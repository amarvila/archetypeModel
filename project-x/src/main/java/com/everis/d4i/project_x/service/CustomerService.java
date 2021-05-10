package com.everis.d4i.project_x.service;

import com.everis.d4i.project_x.controller.rest.model.CustomerRest;
import com.everis.d4i.project_x.exception.SalesException;
import com.everis.d4i.project_x.service.model.CustomerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;

public interface CustomerService {

    Page<CustomerDto> getAllCustomers(Pageable pageable, PagedResourcesAssembler<CustomerRest> assembler)
            throws SalesException;

    CustomerDto createCustomer(CustomerRest Customer) throws SalesException;

    CustomerDto getCustomerById(Long id) throws SalesException;

    CustomerDto updateCustomer(CustomerRest CustomerDetails) throws SalesException;

    void deleteCustomer(Long id) throws SalesException;

}
