package com.db.common.web;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.db.common.exception.ServiceException;
/**
 * 时间访问控制
 * @author TEDU
 *
 */
@Component
public class AccessIntercepter extends HandlerInterceptorAdapter{
  
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("Prohandler");
		long time = System.currentTimeMillis();
		Calendar cd = Calendar.getInstance();
		cd.set(Calendar.HOUR_OF_DAY,15);
		cd.set(Calendar.MINUTE,0);
		cd.set(Calendar.SECOND,0);
		long start = cd.getTimeInMillis();
		cd.set(Calendar.HOUR_OF_DAY,18);
		long end = cd.getTimeInMillis();
		if(time<end&&time>start) {
			throw new ServiceException("不可以访问");
		
			
		}else 
			return true;
	
		
	}
}
