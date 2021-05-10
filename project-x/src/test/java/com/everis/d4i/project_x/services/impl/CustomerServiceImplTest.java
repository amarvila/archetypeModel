package com.everis.d4i.project_x.services.impl;

import com.everis.d4i.project_x.controller.rest.mapper.CustomerRestMapper;
import com.everis.d4i.project_x.controller.rest.model.CustomerRest;
import com.everis.d4i.project_x.exception.SalesException;
import com.everis.d4i.project_x.exception.SalesNotFoundException;
import com.everis.d4i.project_x.persistence.entity.CustomerEntity;
import com.everis.d4i.project_x.persistence.mapper.CustomerEntityMapper;
import com.everis.d4i.project_x.persistence.repository.CustomerRepository;
import com.everis.d4i.project_x.service.impl.CustomerServiceImpl;
import com.everis.d4i.project_x.service.model.CustomerDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;

@RunWith(SpringRunner.class)
@WebAppConfiguration
public class CustomerServiceImplTest {

    static final Long ID = 1L;
    static final CustomerEntity CUSTOMER_ENTITY = new CustomerEntity();
    static final CustomerRest CUSTOMER_REST = new CustomerRest();
    static final CustomerDto CUSTOMER_DTO = new CustomerDto();

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerRestMapper customerRestMapper;

    @Mock
    private CustomerEntityMapper customerEntityMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        CUSTOMER_ENTITY.setId(ID);
        CUSTOMER_DTO.setId(ID);
        CUSTOMER_REST.setId(ID);

        Mockito.when(customerRepository.findById(anyLong())).thenReturn(Optional.of(CUSTOMER_ENTITY));
        Mockito.when(customerRestMapper.mapToRest(any(CustomerDto.class))).thenReturn(CUSTOMER_REST);
        Mockito.when(customerRestMapper.mapToDto(any(CustomerRest.class))).thenReturn(CUSTOMER_DTO);
        Mockito.when(customerEntityMapper.mapToEntity(any(CustomerDto.class))).thenReturn(CUSTOMER_ENTITY);
        Mockito.when(customerEntityMapper.mapToDto(any(CustomerEntity.class))).thenReturn(CUSTOMER_DTO);

    }

    @Test
    public void getAllCustomersTest() throws SalesException {
        Pageable pageable = PageRequest.of(0, 1);
        HateoasPageableHandlerMethodArgumentResolver resolver = new HateoasPageableHandlerMethodArgumentResolver();
        PagedResourcesAssembler<CustomerRest> assembler = new PagedResourcesAssembler<>(resolver, null);

        Page<CustomerEntity> customerPage = new PageImpl<>(List.of(CUSTOMER_ENTITY), pageable, 0);
        Mockito.when(customerRepository.findAll(any(Pageable.class))).thenReturn(customerPage);

        Page<CustomerDto> pagedModel = customerService.getAllCustomers(pageable, assembler);

        assertNotNull(pagedModel);
    }

    @Test
    public void getCustomerByIdTest() throws SalesException {
        CustomerDto response = customerService.getCustomerById(ID);

        assertEquals(ID, response.getId());
    }

    @Test
    public void createCustomerTest() throws SalesException {
        Mockito.when(customerRepository.save(CUSTOMER_ENTITY)).thenReturn(CUSTOMER_ENTITY);

        CustomerDto customerDtoOut = customerService.createCustomer(CUSTOMER_REST);

        assertEquals(CUSTOMER_DTO, customerDtoOut);
        Mockito.verify(customerRepository, Mockito.times(1)).save(Mockito.any(CustomerEntity.class));
    }

    @Test
    public void updateCustomerTest() throws SalesException {
        Mockito.when(customerRepository.save(any())).thenReturn(CUSTOMER_ENTITY);

        CustomerDto customerDtoOut = customerService.updateCustomer(CUSTOMER_REST);
        assertEquals(CUSTOMER_DTO, customerDtoOut);
        Mockito.verify(customerRepository, Mockito.times(1)).save(Mockito.any(CustomerEntity.class));
    }

    @Test(expected = SalesNotFoundException.class)
    public void updateCustomerButNotExists() throws SalesException {

        Mockito.when(customerRepository.findById(ID)).thenReturn(Optional.empty());

        customerService.updateCustomer(CUSTOMER_REST);
    }

    @Test
    public void deleteCustomer() throws SalesException {

        customerService.deleteCustomer(1L);

        Mockito.verify(customerRepository, Mockito.times(1)).deleteById(anyLong());
    }
}