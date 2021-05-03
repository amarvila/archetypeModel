package com.everis.d4i.project_x.persistence.mapper;

import com.everis.d4i.project_x.persistence.entity.CustomerEntity;
import com.everis.d4i.project_x.service.model.CustomerDto;

public class CustomerEntityMapper implements EntityMapper<CustomerEntity, CustomerDto>{
    @Override
    public CustomerEntity mapToEntity(final CustomerDto dto) {
        return new CustomerEntity(dto.getId(), dto.getCode(), dto.getDescription());
    }

    @Override
    public CustomerDto mapToDto(final CustomerEntity entity) {
        return new CustomerDto(entity.getId(), entity.getCode(), entity.getDescription());
    }
}
