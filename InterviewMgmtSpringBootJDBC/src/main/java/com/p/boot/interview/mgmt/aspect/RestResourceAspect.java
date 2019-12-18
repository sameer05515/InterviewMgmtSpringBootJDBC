package com.p.boot.interview.mgmt.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class RestResourceAspect {
	
	private Logger logger = LoggerFactory.getLogger(RestResourceAspect.class);
	
	//What kind of method calls I would intercept
	//execution(* PACKAGE.*.*(..))
	//Weaving & Weaver
	@Before("execution(* com.p.boot.interview.mgmt.rest.resource.*.*(..))")
	public void before(JoinPoint joinPoint){
		//Advice
		logger.info(" Going to execute resource ");
		logger.info(
				"Entered into "
				+ joinPoint.getSignature()
				+ " method");
		logger.info(" Allowed execution for {}", joinPoint);
	}
	
	
	@AfterReturning(value = "execution(* com.p.boot.interview.mgmt.rest.resource.*.*(..))", 
			returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		logger.info("{} returned with value {}", joinPoint, result);
	}
	
	@After(value = "execution(* com.p.boot.interview.mgmt.rest.resource.*.*(..))")
	public void after(JoinPoint joinPoint) {
		logger.info("after execution of {}", joinPoint);
	}
	
	@Around("@annotation(com.p.boot.interview.mgmt.aspect.TrackTime)")
	public void around(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();

		joinPoint.proceed();

		long timeTaken = System.currentTimeMillis() - startTime;
		logger.info("Time Taken by {} is {} milliseconds", joinPoint, timeTaken);
	}
}