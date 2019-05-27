package com.db.commen.config;




import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.alibaba.druid.pool.DruidDataSource;



/**
 * 类似于spring-datasource.xml
 * 使用@Configuration描述的类可以看成一个配置 类，在这个类中可以做一些等效于spring.xml配置中bean对象的配置
 * @author TEDU
 *
 */
@Configuration  //等效于@service,用于配置类对象
@PropertySource("classpath:configs.properties")
public class SpringDateSourceConfig {
	/**
	 * en为spring中的一个环境信息，可以存储一些配置信息，
	 * @return
	 */
	@Lazy(true)
	@Bean(value="dataSource",initMethod="init",destroyMethod="close")   //<bean id="" class=""/>
	public DataSource newdruidDataSource(Environment en) {
		DruidDataSource ds = new DruidDataSource();
		ds.setDriverClassName(en.getProperty("jdbcDriver"));
		ds.setUrl(en.getProperty("jdbcUrl"));
		ds.setUsername(en.getProperty("jdbcUser"));
		ds.setPassword(en.getProperty("jdbcPassword"));
		return ds;
	}
}
