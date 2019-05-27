package com.db.model;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface DaoMapper{
	void save();
}

interface DaoMappers{
	void save();
}
class MyProxyFactory{
	@SuppressWarnings("unchecked")
	public static <T>T  getMapper(Class<T> target) {
		T newProxyInstance = (T) Proxy.newProxyInstance(target.getClassLoader(), 
				new Class[] {target},new MyProxyHandle(target));
		System.out.println(new Class[] {target});
		return newProxyInstance;
	}

	
}
class MyProxyHandle implements InvocationHandler{
     private Class<?> target;
	public MyProxyHandle(Class<?> target) {
    	 this.target = target;
     }
	@Override
	public Class<?> invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return null;
	}
	
}

public class TestProxy03Clone {
	public static void main(String[] args) {
		DaoMapper mapper =  MyProxyFactory.getMapper(DaoMapper.class);
		System.out.println(mapper.getClass());
		DaoMappers mappers =  MyProxyFactory.getMapper(DaoMappers.class);
		System.out.println(mappers.getClass());
	}
}
