package com.everis.d4i.project_x.controller.rest.mapper;

import com.everis.d4i.project_x.controller.rest.model.CustomerRest;
import com.everis.d4i.project_x.service.model.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerRestMapper implements RestMapper<CustomerRest, CustomerDto> {

    @Override
    public CustomerRest mapToRest(final CustomerDto dto) {
        return new CustomerRest(dto.getId(), dto.getCode(), dto.getDescription());
    }

    @Override
    public CustomerDto mapToDto(final CustomerRest rest) {
        return new CustomerDto(rest.getId(), rest.getCode(), rest.getDescription());
    }

}
