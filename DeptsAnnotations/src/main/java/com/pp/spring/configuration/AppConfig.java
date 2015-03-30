package com.pp.spring.configuration;

import java.util.Properties;

import com.pp.spring.interceptors.DeptInterceptor;
import com.pp.spring.interceptors.EmployeeInterceptor;
import com.pp.spring.validate.EmployeeValidator;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.validation.Valid;

@Configuration
@EnableWebMvc
@ComponentScan(value={"com.pp.spring"})
@EnableTransactionManagement
public class AppConfig extends WebMvcConfigurerAdapter {
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
	
	@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public DeptInterceptor deptInterceptor() {
        return new DeptInterceptor();
    }

	@Bean
	public EmployeeInterceptor emplInterceptor() {
		return new EmployeeInterceptor();
	}

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(deptInterceptor())
                .addPathPatterns("/", "/depts");
		registry.addInterceptor(emplInterceptor())
				.addPathPatterns("/employees");
    }

	@Bean
	public SessionFactory sessionFactory() {
		LocalSessionFactoryBuilder builder = 
				new LocalSessionFactoryBuilder(dataSource());
		builder.scanPackages("com.pp.spring.model")
		.addProperties(getHibernateProperties());

		return builder.buildSessionFactory();
	}

	private Properties getHibernateProperties() {
		Properties prop = new Properties();
		prop.put("hibernate.format_sql", "true");
		prop.put("hibernate.show_sql", "true");
		prop.put("hibernate.dialect", 
				"org.hibernate.dialect.MySQL5Dialect");
		
		return prop;
	}

	@Bean(name = "dataSource")
	public BasicDataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/dept");
		ds.setUsername("root");
		ds.setPassword("root");

		return ds;
	}

	//Create a transaction manager
	@Bean
	public HibernateTransactionManager txManager() {
		return new HibernateTransactionManager(sessionFactory());
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver 
		= new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}


	@Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
        resource.setBasename("classpath:message");
        resource.setDefaultEncoding("UTF-8");
        
        return resource;
    }

}
