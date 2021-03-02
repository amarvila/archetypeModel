package com.everis.d4i.project_x.exception.error;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorRest implements Serializable {

	private static final long serialVersionUID = 6067791672383133605L;

	private String code;

	private String message;
}
