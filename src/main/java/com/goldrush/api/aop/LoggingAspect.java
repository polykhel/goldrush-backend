package com.goldrush.api.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    /**
     * Logs the execution time of methods within the specified package.
     *
     * This method is an AspectJ advice that surrounds the execution of
     * all methods in the `com.goldrush.api.web` package. It records the
     * start and end time of method execution and logs the time taken
     * in milliseconds.
     *
     * @param joinPoint the join point providing contextual information
     *                  about the intercepted method call
     * @return the result of the method execution
     * @throws Throwable if an error occurs during method execution
     */
    @Around("execution(* com.goldrush.api.web.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        log.info("{} Method execution time: {}ms", joinPoint.getSignature(), end - start);
        return result;
    }

    /**
     * Measures and logs the execution time of a method annotated with {@code LogExecutionTime}.
     *
     * This method is an AspectJ advice that intercepts the execution of methods annotated
     * with {@code LogExecutionTime}. It records the start and end time of the method execution
     * and logs the elapsed time in milliseconds.
     *
     * @param joinPoint the join point providing contextual information about the intercepted method call
     * @return the result of the method execution
     * @throws Throwable if an error occurs during method execution
     */
    @Around("@annotation(LogExecutionTime)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        log.info("{} executed in {}ms", joinPoint.getSignature(), executionTime);
        return proceed;
    }
}
