package com.spring.test;


import org.junit.After;
import org.junit.Before;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BaseTest {
	
	protected AnnotationConfigApplicationContext ctx;
	@Before
	public void init() {
	ctx =  new AnnotationConfigApplicationContext(ConTest.class);
	}
	@After
	public void close() {
		ctx.close();
	}

}
