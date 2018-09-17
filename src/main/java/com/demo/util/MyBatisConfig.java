package com.demo.util;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.github.pagehelper.PageHelper;

@Configuration
public class MyBatisConfig {
	
	@Autowired
	//@Qualifier("dataSource")   //出现多个dataSource的解决注释
	private DataSource datSource;
	
	@Bean(name="sqlSessionFactory")
	public SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException {
		
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		//mybatis-config.xml配置文件所在路径
		bean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));		
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		//mybatis mapper文件所在路径
		String path = resolver.CLASSPATH_ALL_URL_PREFIX+"/mapper/**.xml";
		bean.setMapperLocations(resolver.getResources(path));
		bean.setDataSource(datSource);
		//实体类所在路径
		bean.setTypeAliasesPackage("com.demo.bean");
		
		//设置分页
		PageHelper page = new PageHelper();
		Properties prop = new Properties();
		prop.setProperty("reasonable", "true");
		prop.setProperty("supportMethodsArguments", "true");
		prop.setProperty("returnPageInfo", "check");
		prop.setProperty("params", "count=countSql");
		page.setProperties(prop);		
		//添加分页
		bean.setPlugins(new Interceptor [] {page});
		return bean;
		
	}

}
