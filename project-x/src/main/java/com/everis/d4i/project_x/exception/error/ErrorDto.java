package com.everis.d4i.project_x.exception.error;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDto implements Serializable {

	private static final long serialVersionUID = -8502900941111111784L;

	@NotNull
	private String code;

	private String message;

	public ErrorDto(final String code) {
		this.code = code;
	}
}
