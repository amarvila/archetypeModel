package com.everis.d4i.project_x.exception.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.everis.d4i.project_x.controller.rest.model.SalesResponse;
import com.everis.d4i.project_x.exception.SalesException;
import com.everis.d4i.project_x.exception.SalesRuntimeException;
import com.everis.d4i.project_x.exception.error.ErrorMessageService;
import com.everis.d4i.project_x.exception.error.ErrorRest;
import com.everis.d4i.project_x.util.constant.ExceptionConstantsUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@ControllerAdvice
@SuppressWarnings({ "rawtypes", "unchecked" })
@Log4j2
@RequiredArgsConstructor
public class SalesExceptionHandler {

	private final ErrorMessageService errorMessageService;

	@ExceptionHandler({ Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public SalesResponse unhandledErrors(final HttpServletRequest req, final Exception ex) {
		logException(ex);
		return new SalesResponse(ExceptionConstantsUtils.ERROR,
				Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage());
	}

	@ExceptionHandler({ SalesException.class })
	@ResponseBody
	public SalesResponse handleException(final HttpServletRequest request, final HttpServletResponse response,
			final SalesException ex) {
		logException(ex);
		response.setStatus(ex.getCode());

		final ErrorRest[] errorRestArray = ex.getErrorDtoCollection().stream().map(
				errorDto -> new ErrorRest(errorDto.getCode(), errorMessageService.getCodes().get(errorDto.getCode())))
				.toArray(ErrorRest[]::new);

		return new SalesResponse(ExceptionConstantsUtils.ERROR, Integer.toString(ex.getCode()), ex.getMessage(),
				errorRestArray);
	}

	@ExceptionHandler({ SalesRuntimeException.class })
	@ResponseBody
	public SalesResponse handleException(final HttpServletRequest request, final HttpServletResponse response,
			final SalesRuntimeException ex) {
		return handleException(request, response, ex.getSalesException());
	}

	private void logException(final Exception exception) {
		log.error(ExceptionUtils.getStackTrace(exception));
	}

}
