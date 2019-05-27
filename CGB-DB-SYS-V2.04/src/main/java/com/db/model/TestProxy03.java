package com.db.model;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface SearchDao{
	String search(String key);
	
}
class DaoProxysFactory{
	@SuppressWarnings("unchecked")
	public static <T>T GetProxy(Class<T> target) {
		//返回一个代理对象
		return (T) Proxy.newProxyInstance(target.getClassLoader(), 
				                      //这里为什么要用数组？
			                           new Class[] {target}, 
			                           new ProxyHandle(target));
	}
}
//处理器
class ProxyHandle implements InvocationHandler{
	private Class<?> target;
	public ProxyHandle(Class<?> target) {
		this.target =  target;
	}
	@Override
	public Class<?> invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String name = target.getName();
		String name2 = method.getName();
		System.out.println(name+"."+name2);
		return null;
	}
}
public class TestProxy03 {
	public static void main(String[] args) {
		SearchDao dao = DaoProxysFactory.GetProxy(SearchDao.class);
		System.out.println(dao.getClass());
		//方法为什么会调用
		dao.search("1");
	}
}
