package com.db.common.Aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Order(1)//切面执行的优先级。数字越小优先级越高
@Aspect
@Service
public class TestAspect {
	@Pointcut("bean(sysUserServiceImpl)")
	public void pointcut() {}
	
   @Before("pointcut()")
   public void beforeMethod() {
	   System.out.println("@before");
   }
   @After("pointcut()")
   public void AfterMathod() {
	   System.out.println("@After");
   }
   @AfterReturning("pointcut()")
   public void AfterReturningMathod() {
	   System.out.println("@AfterReturning");
   };
   @Around("pointcut()") 
   public void AroundMathod(ProceedingJoinPoint jb) throws Throwable {
	   System.out.println("@Around before");
	   Object proceed = jb.proceed();
	   System.out.println("@Around After");
   }
}
