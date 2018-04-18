package com.lxk.util;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan
@PropertySource(value = { "classpath:/error/error.properties" })
public class Error implements EnvironmentAware{
	//@Autowired
	static
	Environment env;
	
	public static String code(String code){
		return env.getProperty(code);
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.env=environment;
	}
}
