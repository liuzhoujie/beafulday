package com.db.common.Aspect;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;

import com.db.common.Cache.CacheKey;

@Aspect
@Service
public class CacheASpect {
	private Map<Object,Object> cache  = new ConcurrentHashMap<>();
	@Around("@annotation(com.db.common.annotation.RequiredCache)")
   public Object aroundCache(ProceedingJoinPoint pj) throws Throwable {
		
		CacheKey key = new CacheKey();
	    Class<?> targetClass = pj.getTarget().getClass();
	    key.setTargetClass(targetClass);
	    MethodSignature ms =(MethodSignature) pj.getSignature();
	    Method method = ms.getMethod();
	    key.setTargetMethod(method);
//	    Class<?>[] parameterTypes = ms.getMethod().getParameterTypes();
	    key.setArgs(pj.getArgs());
		
	   Object result = cache.get(key);
	   if(result==null) {
		   result = pj.proceed();
		   cache.put(key,result);
		 
	   }
	return result;
   }
}
