package com.db.model;


import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

class ProductServiceImpl{
	public void saveObject() {
		System.out.println("save product");
	}
}
class CglibProxyFactory implements MethodInterceptor{
	
	private Object target;
	
	public CglibProxyFactory(Object target){
		this.target = target;
	}
	
	//给目标对象创建一个动态代理
	public   Object getProductProxy() {
		//1 创建工具类
		Enhancer en = new Enhancer();
		
		//2 设置父类
		en.setSuperclass(target.getClass());
		//3 设置回调函数
		en.setCallback(this);
		//4 创建子类(代理对象)
		return en.create();	
	}
  //arg0代理对象，method目标方法，args实际参数
	@Override
	public Object intercept(Object arg0, Method method, Object[] args, MethodProxy arg3) throws Throwable {
		System.out.println("Cglib为A");
		Object invoke = method.invoke(target, args);//需要传入目标对象的实例
		/*arg3.invokeSuper(arg0, args);*/
		System.out.println("Cglib为B");
		return invoke;
	}
	
}
public class CglibProxy {
       public static void main(String[] args) {
    	   ProductServiceImpl psi = new ProductServiceImpl();
    	   ProductServiceImpl productProxy = (ProductServiceImpl) new CglibProxyFactory(psi).getProductProxy();
    	   productProxy.saveObject();
       }
}
