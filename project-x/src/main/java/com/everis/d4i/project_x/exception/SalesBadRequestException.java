package com.everis.d4i.project_x.exception;

import java.util.Collection;

import org.springframework.http.HttpStatus;

import com.everis.d4i.project_x.exception.error.ErrorDto;

import lombok.Getter;

@Getter
public class SalesBadRequestException extends SalesException {

	private static final long serialVersionUID = 105837498733124083L;

	public SalesBadRequestException(final Collection<ErrorDto> errorDtoCollection) {
		super(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), errorDtoCollection);
	}

	public SalesBadRequestException(final ErrorDto errorDto) {
		super(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), errorDto);
	}

}
