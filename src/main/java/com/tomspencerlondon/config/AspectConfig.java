package com.tomspencerlondon.config;

import com.tomspencerlondon.entity.AuditLog;
import com.tomspencerlondon.repository.AuditRepository;
import com.tomspencerlondon.repository.VehicleRepository;
import java.util.Date;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

@Slf4j
@Aspect
@Configuration
public class AspectConfig {
	//  Homework - in Aspect Config class
//  Add entry to audit log table each time
//  employee updated in employee table
	// Later we will do rate limiting

	@Autowired
	AuditRepository auditRepository;

// Pointcut expression accessModifier returnType
// packageName.className.methodName(..)
	@Before("execution(public * com.tomspencerlondon.serviceimpl.*.*(..) )")
	public void logBeforeAllMethods(JoinPoint joinPoint) {
		String description = joinPoint.getSignature().getName() + " Started";
		log.info(description);
		auditRepository.save(
				AuditLog.builder()
						.id(1)
						.createDate(new Date())
						.description(description)
						.build());
	}

//	@AfterReturning("execution(public * com.example.aop.serviceImpl.EmployeeServiceImpl.addEmployee(..) )")
//	public void logBeforeAddEmployee(JoinPoint joinPoint) {
//		auditRepository.saveAndFlush(AuditLog.builder().createDate(new Date())
//				.discription("Details of employee added " + joinPoint.getArgs()[0]).build());

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
