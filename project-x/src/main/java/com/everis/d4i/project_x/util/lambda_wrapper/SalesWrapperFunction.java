package com.everis.d4i.project_x.util.lambda_wrapper;

import java.util.function.Function;

import com.everis.d4i.project_x.exception.SalesException;
import com.everis.d4i.project_x.exception.SalesRuntimeException;

@FunctionalInterface
public interface SalesWrapperFunction<T, R, E extends SalesException> {

	R apply(T item) throws E;

	static <T, R, E extends SalesException> Function<T, R> wrap(final SalesWrapperFunction<T, R, E> wrapper) {
		return item -> {
			try {
				return wrapper.apply(item);
			} catch (final SalesException exception) {
				throw new SalesRuntimeException(exception);
			}
		};
	}

}
