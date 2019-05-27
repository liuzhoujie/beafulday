package com.db.commen.config;

import java.io.IOException;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan("com.db.**.dao")//扫描指定包下的Dao
public class SpringMyBatis {
	@Bean("sqlSessionFactory")
	SqlSessionFactoryBean newsqlFactory(@Autowired DataSource ds) throws IOException {
		 SqlSessionFactoryBean fBean=new SqlSessionFactoryBean();
		 fBean.setDataSource(ds);
		 fBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/sys/*Mapper.xml"));
		return fBean;
		
	}
}
