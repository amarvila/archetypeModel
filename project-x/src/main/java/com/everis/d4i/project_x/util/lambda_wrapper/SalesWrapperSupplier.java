package com.everis.d4i.project_x.util.lambda_wrapper;

import java.util.function.Supplier;

import com.everis.d4i.project_x.exception.SalesException;
import com.everis.d4i.project_x.exception.SalesRuntimeException;

@FunctionalInterface
public interface SalesWrapperSupplier<T, E extends SalesException> {

	T get() throws E;

	static <T, E extends SalesException> Supplier<T> wrap(final SalesWrapperSupplier<T, E> wrapper) {
		return () -> {
			try {
				return wrapper.get();
			} catch (final SalesException exception) {
				throw new SalesRuntimeException(exception);
			}
		};
	}

}
