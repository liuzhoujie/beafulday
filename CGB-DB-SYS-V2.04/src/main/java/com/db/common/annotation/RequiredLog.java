package com.db.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 
 * @author TEDU
 *@Retention:描述注解何时有效
 *@Target：描述注解可以修饰哪些成员
 * 注解的作用：描述类和它的成员，可以定义时何时有效
 */
//设置为运行时有效
@Retention(RetentionPolicy.RUNTIME)
//方法有效
@Target(ElementType.METHOD)
public @interface RequiredLog {
	//理解为注解内部定义的属性
    String value() default "";
}
