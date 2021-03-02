package com.everis.d4i.project_x.util.lambda_wrapper;

import java.util.function.Predicate;

import com.everis.d4i.project_x.exception.SalesException;
import com.everis.d4i.project_x.exception.SalesRuntimeException;

@FunctionalInterface
public interface SalesWrapperPredicate<T, E extends SalesException> {

	boolean test(T item) throws E;

	static <T, E extends SalesException> Predicate<T> wrap(final SalesWrapperPredicate<T, E> wrapper) {
		return item -> {
			try {
				return wrapper.test(item);
			} catch (final SalesException exception) {
				throw new SalesRuntimeException(exception);
			}
		};
	}

}
