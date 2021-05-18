package com.everis.d4i.project_x.controllers.impl;

import com.everis.d4i.project_x.controller.rest.impl.CustomerControllerRestImpl;
import com.everis.d4i.project_x.controller.rest.mapper.CustomerRestMapper;
import com.everis.d4i.project_x.controller.rest.model.CustomerRest;
import com.everis.d4i.project_x.controller.rest.model.D4iPageRest;
import com.everis.d4i.project_x.controller.rest.model.SalesResponse;
import com.everis.d4i.project_x.exception.SalesException;
import com.everis.d4i.project_x.persistence.entity.CustomerEntity;
import com.everis.d4i.project_x.service.CustomerService;
import com.everis.d4i.project_x.service.model.CustomerDto;
import com.everis.d4i.project_x.util.constant.RestConstantsUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerRestImplTest {

    static final Long ID = 1L;
    static final CustomerEntity CUSTOMER_ENTITY = new CustomerEntity();
    static final CustomerRest CUSTOMER_REST = new CustomerRest();
    static final CustomerDto CUSTOMER_DTO = new CustomerDto();

    @Mock
    private CustomerService customerService;

    @Mock
    private CustomerRestMapper customerRestMapper;

    @InjectMocks
    private CustomerControllerRestImpl customerControllerRestImpl;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        CUSTOMER_ENTITY.setId(ID);
        CUSTOMER_REST.setId(ID);
    }

    @Test
    public void getAllCustomersTest() throws SalesException {
        // given
        int page = 0;
        int size = 2;
        int pageNumber = 1;
        Pageable pageable = PageRequest.of(page, size);
        HateoasPageableHandlerMethodArgumentResolver resolver = new HateoasPageableHandlerMethodArgumentResolver();

        Page<CustomerDto> customersPagedModel = new PageImpl<>(List.of(new CustomerDto()));
        Mockito.when(customerService.getAllCustomers(any(Pageable.class))).thenReturn(customersPagedModel);
        Mockito.when(customerRestMapper.mapToRest(any(CustomerDto.class))).thenReturn(CUSTOMER_REST);

        // when
        SalesResponse<D4iPageRest<CustomerRest>> response = customerControllerRestImpl.getAllCustomers(page, size, pageable);

        // then
        assertNotNull(response);
        assertEquals(RestConstantsUtils.OK, response.getMessage());
        assertNotNull(response.getData().getContent());
    }

    @Test
    public void getCustomerByIdTest() throws SalesException {
        Mockito.when(customerService.getCustomerById(anyLong())).thenReturn(CUSTOMER_DTO);
        Mockito.when(customerRestMapper.mapToRest(any(CustomerDto.class))).thenReturn(CUSTOMER_REST);

        SalesResponse<CustomerRest> response = customerControllerRestImpl.getCustomerById(ID);

        assertNotNull(response);
        assertEquals(String.valueOf(HttpStatus.OK), response.getStatus());
        assertEquals(RestConstantsUtils.OK, response.getMessage());
        assertEquals(CUSTOMER_REST, response.getData());
    }

    @Test
    public void createCustomerTest() throws SalesException {
        Mockito.when(customerService.createCustomer(any(CustomerDto.class))).thenReturn(CUSTOMER_DTO);
        Mockito.when(customerRestMapper.mapToDto(any(CustomerRest.class))).thenReturn(CUSTOMER_DTO);
        Mockito.when(customerRestMapper.mapToRest(any(CustomerDto.class))).thenReturn(CUSTOMER_REST);

        SalesResponse<CustomerRest> response = customerControllerRestImpl.createCustomer(CUSTOMER_REST);

        assertNotNull(response);
        assertEquals(String.valueOf(HttpStatus.OK), response.getStatus());
        assertEquals("200", response.getCode());
        assertEquals(RestConstantsUtils.OK, response.getMessage());
        assertEquals(CUSTOMER_REST, response.getData());
    }

    @Test
    public void deleteCustomerTest() throws SalesException {
        customerControllerRestImpl.deleteCustomer(ID);

        Mockito.verify(customerService, Mockito.times(1)).deleteCustomer(Mockito.anyLong());

    }

    @Test
    public void updateCustomerTest() throws SalesException {
        CustomerDto customerDtoModified = new CustomerDto();
        customerDtoModified.setId(2L);
        Mockito.when(customerService.updateCustomer(any(CustomerDto.class))).thenReturn(customerDtoModified);

        SalesResponse<CustomerRest> response = customerControllerRestImpl.updateCustomer(CUSTOMER_REST);

        assertNotNull(response);
        assertEquals(String.valueOf(HttpStatus.OK), response.getStatus());
        assertEquals("200", response.getCode());
        assertEquals(RestConstantsUtils.OK, response.getMessage());
    }
}
