package com.everis.d4i.project_x.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import com.everis.d4i.project_x.exception.error.ErrorDto;

import java.util.Collection;

@Getter
public class SalesNotFoundException extends SalesException {

	private static final long serialVersionUID = 1419856382856533644L;

	public SalesNotFoundException(final ErrorDto errorDto) {
		super(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), errorDto);
	}

	public SalesNotFoundException(final Collection<ErrorDto> errorDtoCollection) {
		super(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), errorDtoCollection);
	}

}
