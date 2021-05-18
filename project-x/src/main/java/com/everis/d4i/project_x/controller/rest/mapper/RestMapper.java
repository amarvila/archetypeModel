package com.everis.d4i.project_x.controller.rest.mapper;

import java.io.Serializable;

public interface RestMapper<R extends Serializable, D extends Serializable> {

	R mapToRest(D dto);

	D mapToDto(R rest);

}
