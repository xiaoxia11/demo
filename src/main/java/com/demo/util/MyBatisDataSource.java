package com.demo.util;

import java.beans.PropertyVetoException;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
//配置mybatis mapper的扫描路径
@MapperScan("com.demo.dao")
public class MyBatisDataSource {
	
	@Value("${spring.datasource.url}")
	private String jdbcUrl;
	@Value("${spring.datasource.driver-class-name}")
	private String driverNameClass;
	@Value("${spring.datasource.username}")
	private String userName;
	@Value("${spring.datasource.password}")
	private String passWord;
	
	@Bean(name="datSource")
	public ComboPooledDataSource creatSource() throws PropertyVetoException {
		ComboPooledDataSource source = new ComboPooledDataSource();
		source.setDriverClass(driverNameClass);
		source.setUser(userName);
		source.setPassword(passWord);
		source.setJdbcUrl(jdbcUrl);
		//关闭连接后不自动提交
		source.setAutoCommitOnClose(false);
		return source;
	}

}
