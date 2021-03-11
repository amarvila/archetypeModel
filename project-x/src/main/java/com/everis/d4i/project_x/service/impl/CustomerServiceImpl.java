package com.everis.d4i.project_x.service.impl;

import com.everis.d4i.project_x.controller.rest.model.D4iPageRest;
import com.everis.d4i.project_x.controller.rest.model.D4iPaginationInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.stereotype.Service;

import com.everis.d4i.project_x.controller.rest.model.CustomerRest;
import com.everis.d4i.project_x.exception.SalesException;
import com.everis.d4i.project_x.exception.SalesNotFoundException;
import com.everis.d4i.project_x.exception.error.ErrorDto;
import com.everis.d4i.project_x.persistence.entity.CustomerEntity;
import com.everis.d4i.project_x.persistence.repository.CustomerRepository;
import com.everis.d4i.project_x.service.CustomerService;
import com.everis.d4i.project_x.util.constant.ExceptionConstantsUtils;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public D4iPageRest<CustomerRest> getAllCustomers(final Pageable pageable,
                                                     final PagedResourcesAssembler<CustomerRest> assembler) throws SalesException {
	Page<CustomerEntity> customerPage = customerRepository.findAll(pageable);
	Page<CustomerRest> customerRestList = customerPage.map(customer -> modelMapper.map(customer, CustomerRest.class));
	return new D4iPageRest<>(customerRestList.getContent().toArray(CustomerRest[]::new),
                             new D4iPaginationInfo(customerRestList.getNumber(),
                                                   pageable.getPageSize(),
                                                   customerRestList.getTotalPages()));
    }

    @Override
    public CustomerRest createCustomer(final CustomerRest customerRest) throws SalesException {
	CustomerEntity customerEntity = modelMapper.map(customerRest, CustomerEntity.class);
	return modelMapper.map(customerRepository.save(customerEntity), CustomerRest.class);
    }

    @Override
    public CustomerRest getCustomerById(final Long id) throws SalesException {
	CustomerEntity customer = customerRepository.findById(id)
		.orElseThrow(() -> new SalesNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
	return modelMapper.map(customer, CustomerRest.class);
    }

    @Override
    public CustomerRest updateCustomer(final CustomerRest customerRest) throws SalesException {
	CustomerEntity customer = customerRepository.findById(customerRest.getId())
		.orElseThrow(() -> new SalesNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));

	return modelMapper.map(customerRepository.save(updateCustomer(customer, customerRest)), CustomerRest.class);
    }

    @Override
    public void deleteCustomer(final Long id) throws SalesException {
	customerRepository.deleteById(id);
    }

    private CustomerEntity updateCustomer(final CustomerEntity customer, final CustomerRest customerRest) {
	if (customerRest.getCode() != null) {
	    customer.setCode(customerRest.getCode());
	}
	if (customerRest.getDescription() != null) {
	    customer.setDescription(customerRest.getDescription());
	}
	return customer;
    }
}
