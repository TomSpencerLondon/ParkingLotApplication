package com.tomspencerlondon.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Aspect
@Configuration
public class AspectConfig {
	//  Homework - in Aspect Config class
//  Add entry to audit log table each time
//  employee updated in employee table
	// Later we will do rate limiting


// Pointcut expression accessModifier returnType
// packageName.className.methodName(..)
	@Before("execution(public * com.tomspencerlondon.serviceimpl.*.*(..) )")
	public void logBeforeAllMethods(JoinPoint joinPoint) {
		log.info(joinPoint.getSignature().getName() + " Started");
	}

	@After("execution(public * com.tomspencerlondon.serviceimpl.*.*(..) )")
	public void logAfterAllMethods(JoinPoint joinPoint) {
		log.info(joinPoint.getSignature().getName() + " completed");
	}

	@Around("execution(public * com.tomspencerlondon.serviceimpl.ParkingLotJpaServiceImpl.updateVehicle(..) )")
	public void logAroundUpdateVehicle(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		log.info(proceedingJoinPoint.getSignature().getName() + " started");
		proceedingJoinPoint.proceed();
		log.info(proceedingJoinPoint.getSignature().getName() + " ended");
	}

	@AfterThrowing("execution(public * com.tomspencerlondon.serviceimpl.ParkingLotJpaServiceImpl.updateVehicle(..) )")
	public void logOnThrowingExceptionForUpdateVehicle(JoinPoint joinPoint) throws Throwable {
		log.info(joinPoint.getSignature().getName() + " threw exception");
	}

	@AfterReturning("execution(public * com.tomspencerlondon.serviceimpl.ParkingLotJpaServiceImpl.updateVehicle(..) )")
	public void logAfterSuccessfulUpdateVehicle(JoinPoint joinPoint) throws Throwable {
		log.info(joinPoint.getSignature().getName() + " updated successfully");
	}
}
