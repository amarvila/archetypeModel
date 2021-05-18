package com.everis.d4i.project_x.controller.rest.mapper;

import com.everis.d4i.project_x.controller.rest.model.CustomerRest;
import com.everis.d4i.project_x.service.model.CustomerDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

public class CustomerRestMapperTest {

	@InjectMocks
	private CustomerRestMapper customerRestMapper;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testMapToRest() {
		Long id = 1L;
		String code = "0000";
		String description = "ABC";

		CustomerDto customerDto = Mockito.mock(CustomerDto.class);

		Mockito.when(customerDto.getId()).thenReturn(id);
		Mockito.when(customerDto.getCode()).thenReturn(code);
		Mockito.when(customerDto.getDescription()).thenReturn(description);

		CustomerRest result = customerRestMapper.mapToRest(customerDto);

		assertEquals(id, result.getId());
		assertEquals(code, result.getCode());
		assertEquals(description, result.getDescription());

	}

	@Test
	public void testMapToDto() {
		Long id = 1L;
		String code = "0000";
		String description = "ABC";

		CustomerRest customerRest = Mockito.mock(CustomerRest.class);

		Mockito.when(customerRest.getId()).thenReturn(id);
		Mockito.when(customerRest.getCode()).thenReturn(code);
		Mockito.when(customerRest.getDescription()).thenReturn(description);

		CustomerDto result = customerRestMapper.mapToDto(customerRest);

		assertEquals(id, result.getId());
		assertEquals(code, result.getCode());
		assertEquals(description, result.getDescription());
	}

}
