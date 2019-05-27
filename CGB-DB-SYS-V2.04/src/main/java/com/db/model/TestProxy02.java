package com.db.model;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * target:目标对象
 * @author TEDU
 *
 */
interface SearchMessages{
	String searchA();
}
class SearchMessageImpl implements SearchMessages{

	@Override
	public String searchA() {
		System.out.println("这是搜索A信息");
		return "success";
	}
	
}



class ProxyFactory{
	public static Object newproxy(Object target) {
		return Proxy.newProxyInstance(
				target.getClass().getClassLoader(),//loader类加载器
				target.getClass().getInterfaces(),//要实现的接口
				new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						System.out.println("开启事物");
						Object invoke = method.invoke(target, args);
						System.out.println("结束事物");
						return invoke;
					}
				});//处理器
	}
}

public class TestProxy02 {
    public static void main(String[] args) {
    	
    	MessgaeService proxys1  = (MessgaeService)ProxyFactory.newproxy(new MessageServiceImpl());
		
    	SearchMessages proxys2  = (SearchMessages)ProxyFactory.newproxy(new SearchMessageImpl());
    	
    	proxys1.Sendmassage("it is Test");
    	
    	proxys2.searchA();
	}
}
