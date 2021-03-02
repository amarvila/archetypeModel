package com.everis.d4i.project_x.exception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.everis.d4i.project_x.exception.error.ErrorDto;

@Getter
public class SalesException extends Exception {

	private static final long serialVersionUID = 6173659257108201607L;

	private final int code;

	private final String message;

	private final Collection<ErrorDto> errorDtoCollection = new ArrayList<>();

	public SalesException(final int code, final String message, final Collection<ErrorDto> errorDtoCollection) {
		super(message);
		this.code = code;
		this.message = message;
		this.errorDtoCollection.addAll(errorDtoCollection);
	}

	public SalesException(final int code, final String message, final ErrorDto errorDto) {
		this(code, message, Arrays.asList(errorDto));
	}
}
