package com.spring.test;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component("proxyfactory")
public class ProxyfactoryBean implements FactoryBean<proxyfactory> {

	@Override
	public proxyfactory getObject() throws Exception {
		// TODO Auto-generated method stub
		return new proxyfactory();
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return false;
	}

}
