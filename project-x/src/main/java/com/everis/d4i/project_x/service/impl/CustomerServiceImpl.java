package com.everis.d4i.project_x.service.impl;

import com.everis.d4i.project_x.controller.rest.model.CustomerRest;
import com.everis.d4i.project_x.exception.SalesException;
import com.everis.d4i.project_x.exception.SalesNotFoundException;
import com.everis.d4i.project_x.exception.error.ErrorDto;
import com.everis.d4i.project_x.persistence.entity.CustomerEntity;
import com.everis.d4i.project_x.persistence.mapper.CustomerEntityMapper;
import com.everis.d4i.project_x.persistence.repository.CustomerRepository;
import com.everis.d4i.project_x.service.CustomerService;
import com.everis.d4i.project_x.service.model.CustomerDto;
import com.everis.d4i.project_x.util.constant.ExceptionConstantsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private CustomerEntityMapper customerEntityMapper;

    @Override
    public Page<CustomerDto> getAllCustomers(final Pageable pageable,
                                             final PagedResourcesAssembler<CustomerRest> assembler) throws SalesException {
        return customerRepository.findAll(pageable).map(customerEntity -> customerEntityMapper.mapToDto(customerEntity));
    }

    @Override
    public CustomerDto createCustomer(final CustomerDto customerDto) throws SalesException {

        CustomerEntity customerEntity = customerRepository.save(customerEntityMapper.mapToEntity(customerDto));
        return customerEntityMapper.mapToDto(customerEntity);
    }

    @Override
    public CustomerDto getCustomerById(final Long id) throws SalesException {
        CustomerEntity customer = customerRepository.findById(id)
                .orElseThrow(() -> new SalesNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        return customerEntityMapper.mapToDto(customer);
    }

    @Override
    public CustomerDto updateCustomer(final CustomerDto customerDto) throws SalesException {
        CustomerEntity customer = customerRepository.findById(customerDto.getId())
                .orElseThrow(() -> new SalesNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        CustomerEntity customerEntity = customerRepository.save(customerEntityMapper.mapToEntity(customerDto));
        return customerEntityMapper.mapToDto(customerEntity);
    }

    @Override
    public void deleteCustomer(final Long id) throws SalesException {
        customerRepository.deleteById(id);
    }

}
