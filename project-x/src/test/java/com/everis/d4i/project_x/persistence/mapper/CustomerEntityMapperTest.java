package com.everis.d4i.project_x.persistence.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.everis.d4i.project_x.persistence.entity.CustomerEntity;
import com.everis.d4i.project_x.service.model.CustomerDto;

@RunWith(MockitoJUnitRunner.class)
public class CustomerEntityMapperTest {

	@InjectMocks
	private CustomerEntityMapper customerEntityMapper;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testMapToEntity() {
		Long id = 1L;
		String code = "0000";
		String description = "ABC";

		CustomerDto customerDto = Mockito.mock(CustomerDto.class, Mockito.RETURNS_DEEP_STUBS);

		Mockito.when(customerDto.getId()).thenReturn(id);
		Mockito.when(customerDto.getCode()).thenReturn(code);
		Mockito.when(customerDto.getDescription()).thenReturn(description);

		CustomerEntity result = customerEntityMapper.mapToEntity(customerDto);

		assertEquals(id, result.getId());
		assertEquals(code, result.getCode());
		assertEquals(description, result.getDescription());
	}

	@Test
	public void testMapToDto() {
		Long id = 1L;
		String code = "0000";
		String description = "ABC";

		CustomerEntity customerEntity = Mockito.mock(CustomerEntity.class, Mockito.RETURNS_DEEP_STUBS);

		Mockito.when(customerEntity.getId()).thenReturn(id);
		Mockito.when(customerEntity.getCode()).thenReturn(code);
		Mockito.when(customerEntity.getDescription()).thenReturn(description);

		CustomerDto result = customerEntityMapper.mapToDto(customerEntity);

		assertEquals(id, result.getId());
		assertEquals(code, result.getCode());
		assertEquals(description, result.getDescription());
	}

}
