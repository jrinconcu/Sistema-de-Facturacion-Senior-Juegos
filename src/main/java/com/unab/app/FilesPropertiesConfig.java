package com.unab.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.unab.app.security.RepositoryUserDetailService;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

@Configuration
@PropertySources({@PropertySource({"classpath:textos.properties", "classpath:mysql.properties"})})
public class FilesPropertiesConfig {

	@Autowired
	private RepositoryUserDetailService userDetailsService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	DaoAuthenticationProvider DaoAuthenticationProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
}
