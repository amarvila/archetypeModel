package com.everis.d4i.project_x.util.lambda_wrapper;

import java.util.function.Consumer;

import com.everis.d4i.project_x.exception.SalesException;
import com.everis.d4i.project_x.exception.SalesRuntimeException;

@FunctionalInterface
public interface SalesWrapperConsumer<T, E extends SalesException> {

	void accept(T item) throws E;

	static <T, E extends SalesException> Consumer<T> wrap(final SalesWrapperConsumer<T, E> wrapper) {
		return item -> {
			try {
				wrapper.accept(item);
			} catch (final SalesException exception) {
				throw new SalesRuntimeException(exception);
			}
		};
	}

}
