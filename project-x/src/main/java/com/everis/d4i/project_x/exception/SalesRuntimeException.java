package com.everis.d4i.project_x.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SalesRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 475941979339214878L;

	private SalesException salesException;

}
