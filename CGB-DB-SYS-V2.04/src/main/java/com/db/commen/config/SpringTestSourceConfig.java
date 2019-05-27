package com.db.commen.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.alibaba.druid.pool.DruidDataSource;

/*@Lazy(true)
@Configuration
@PropertySource("classpath:configs.properties")
public class SpringTestSourceConfig {
	@Bean(value="dataSource",initMethod="init",destroyMethod="close")
	public DataSource getDataSourec(Environment en) {
		DruidDataSource ds = new DruidDataSource(en.getProperty(key));
		ds.setDriverClassName(en.getProperty(key));
		ds.setUrl(en.getProperty(key));
		ds.setUsername(en.getProperty(key));
		ds.setPassword(en.getProperty(key));
		
		return ds;
		
	}*/

/*}*/
