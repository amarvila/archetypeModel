package com.everis.d4i.project_x.service;

import com.everis.d4i.project_x.exception.SalesException;
import com.everis.d4i.project_x.service.model.CustomerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    Page<CustomerDto> getAllCustomers(Pageable pageable)
            throws SalesException;

    CustomerDto createCustomer(CustomerDto customer) throws SalesException;

    CustomerDto getCustomerById(Long id) throws SalesException;

    CustomerDto updateCustomer(CustomerDto customerDetails) throws SalesException;

    void deleteCustomer(Long id) throws SalesException;

}
