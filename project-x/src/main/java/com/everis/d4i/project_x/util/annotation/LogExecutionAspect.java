package com.everis.d4i.project_x.util.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;

@Aspect
@Component
@Log
public class LogExecutionAspect {

	private static final String MILLISECONDS = "ms";

	private static final String EXECUTED_IN = " executed in ";

	@Around("@annotation(LogExecutionTime)")
	public Object logExecutionTime(final ProceedingJoinPoint joinPoint) throws Throwable {
		final long start = System.currentTimeMillis();

		final Object proceed = joinPoint.proceed();

		final long executionTime = System.currentTimeMillis() - start;

		log.info(joinPoint.getSignature() + EXECUTED_IN + executionTime + MILLISECONDS);

		return proceed;
	}

}
