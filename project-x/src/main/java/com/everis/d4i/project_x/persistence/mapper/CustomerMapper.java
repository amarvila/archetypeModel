package com.everis.d4i.project_x.persistence.mapper;

import java.io.Serializable;

public interface CustomerMapper<E extends Serializable, D extends Serializable> {

	E mapToEntity(D dto);

	D mapToDto(E entity);

}
