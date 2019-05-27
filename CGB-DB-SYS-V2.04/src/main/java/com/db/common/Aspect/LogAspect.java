package com.db.common.Aspect;



import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;


import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.common.annotation.RequiredLog;
import com.db.common.util.IPUtils;
import com.db.sys.dao.SysLogDao;
import com.db.sys.entity.SysLog;
import com.db.sys.entity.SysUser;

@Aspect
@Service
public class LogAspect {
	/**
	 * 
	 * @param jp
	 * @return
	 * @throws Throwable
	 */
	@Autowired
	private SysLogDao sysLogDao;
	
	/**粗粒度*/
	/*@Around("bean(*ServiceImpl)")//从spring容器中取名字是sysLogServiceImpl的bean对象*/
    //@Around("within(com.db.sys.service.impl.*)")
	 //在业务方法上加上注解
	 @Around("@annotation(com.db.common.annotation.RequiredLog)")
         public Object around(ProceedingJoinPoint jp)throws Throwable{
        	long T1 = System.nanoTime();
        	 Object proceed = jp.proceed();//执行目标方法
        	 long T2 = System.nanoTime();
        	 long time= T2-T1;
        	 saveLog(jp,time);
			return proceed;
         }

	private void saveLog(ProceedingJoinPoint jp,long time) throws NoSuchMethodException, SecurityException {
		//1 获取用户的操作信息
		//1.1获取登录用户
		SysUser user =(SysUser)SecurityUtils.getSubject().getPrincipal();
//		System.out.println(user);
		String username = user.getUsername();
		
		//1.2获取目标方法名
		Class<?> targetClass = jp.getTarget().getClass();
		String className = targetClass.getName();
		
		MethodSignature mss=(MethodSignature)jp.getSignature();//方法签名
		
		String method = className+"."+mss;
//		System.out.println(method);
		//1.3获取执行目标方法传入的参数
		Object[] args = jp.getArgs();
		String params = Arrays.toString(args);
		//1.4获取ip地址
		String ip = IPUtils.getIpAddr();
		
		//1.5获取操作名   :  不用@annotation，就要判断该方法上面有咩有注解
	 	   //获取接口声明的方法
		/**方法名*/
    	String methodName=mss.getMethod().getName();
    	/**方法参数*/
    	Class<?>[] parameterTypes=mss.getMethod().getParameterTypes();
		   //获取方法对象
    	//获取目标对象方法
    	Method targetMethod=targetClass.getDeclaredMethod(
    			    methodName,parameterTypes);
		   //获取方法对象上注解
		   RequiredLog requestLog = targetMethod.getDeclaredAnnotation(RequiredLog.class);
		   //获取注解中的操作名
		   String operation = requestLog.value();   
		//2 封装用户信息
		SysLog log = new SysLog();
		log.setUsername(username);
		log.setTime(time);
	    log.setOperation(operation);
		log.setMethod(method);
		log.setParams(params);
		log.setCreatedTime(new Date());
		int insertObject = sysLogDao.insertObject(log);
		System.out.println(insertObject);
		log.setIp(ip);
	}
}
