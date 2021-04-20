package com.everis.d4i.project_x.controllers.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Matchers.*;

import java.util.ArrayList;
import java.util.Collection;

import com.everis.d4i.project_x.controller.rest.model.D4iPageRest;
import com.everis.d4i.project_x.controller.rest.model.D4iPaginationInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;

import com.everis.d4i.project_x.persistence.entity.CustomerEntity;
import com.everis.d4i.project_x.controller.rest.impl.CustomerControllerRestImpl;
import com.everis.d4i.project_x.controller.rest.model.SalesResponse;
import com.everis.d4i.project_x.controller.rest.model.CustomerRest;
import com.everis.d4i.project_x.exception.SalesException;
import com.everis.d4i.project_x.service.CustomerService;
import com.everis.d4i.project_x.util.constant.RestConstantsUtils;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerRestImplTest {

	static final Long ID = 1L;
	static final CustomerEntity CUSTOMER_ENTITY = new CustomerEntity();
	static final CustomerRest CUSTOMER_REST = new CustomerRest();

    @Mock
    private CustomerService customerService;

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
		PagedResourcesAssembler<CustomerRest> assembler = new PagedResourcesAssembler<>(resolver, null);

		CustomerRest customerRest2 = new CustomerRest();
		customerRest2.setId(2L);

		Collection<CustomerRest> customerEntitiyModelCollection = new ArrayList<>();
		customerEntitiyModelCollection.add(CUSTOMER_REST);
		customerEntitiyModelCollection.add(customerRest2);

		D4iPageRest<CustomerRest> customersPagedModel = new D4iPageRest<>(customerEntitiyModelCollection.toArray(CustomerRest[]::new),
																		  new D4iPaginationInfo(page, size, 1));
		Mockito.when(customerService.getAllCustomers(any(Pageable.class),any(PagedResourcesAssembler.class))).thenReturn(customersPagedModel);

		// when
		SalesResponse<D4iPageRest<CustomerRest>> response = customerControllerRestImpl.getAllCustomers(page,size,pageable,assembler);

		// then
		assertNotNull(response);
		assertEquals(RestConstantsUtils.OK, response.getMessage());
		assertNotNull(response.getData().getContent());
	}

    @Test
    public void getCustomerByIdTest() throws SalesException {
		Mockito.when(customerService.getCustomerById(anyLong())).thenReturn(CUSTOMER_REST);

		SalesResponse<CustomerRest> response = customerControllerRestImpl.getCustomerById(ID);

		assertNotNull(response);
		assertEquals(String.valueOf(HttpStatus.OK), response.getStatus());
		assertEquals(RestConstantsUtils.OK, response.getMessage());
		assertEquals(CUSTOMER_REST, response.getData());
	}

    @Test
    public void createCustomerTest() throws SalesException {
		Mockito.when(customerService.createCustomer(any(CustomerRest.class))).thenReturn(CUSTOMER_REST);

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
		CustomerRest customerRestModified = new CustomerRest();
		customerRestModified.setId(2L);
		Mockito.when(customerService.updateCustomer(any(CustomerRest.class))).thenReturn(customerRestModified);

		SalesResponse<CustomerRest> response = customerControllerRestImpl.updateCustomer(CUSTOMER_REST);

		assertNotNull(response);
		assertEquals(String.valueOf(HttpStatus.OK), response.getStatus());
		assertEquals("200" , response.getCode());
		assertEquals( RestConstantsUtils.OK, response.getMessage());
		assertEquals(customerRestModified, response.getData());
		assertNotEquals(CUSTOMER_REST,response.getData());
	}
}
