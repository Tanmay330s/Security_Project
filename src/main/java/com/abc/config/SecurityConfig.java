package com.abc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterSecurity(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(req->
						req.requestMatchers("/","/about","/contactus","/login","/signup","/save").permitAll()
						   .requestMatchers("/product").authenticated()
						   .requestMatchers("/admin").hasAnyAuthority("admin")
						   .requestMatchers("/super").hasAuthority("superadmin"))
		
						   .httpBasic(Customizer.withDefaults())
						   .formLogin(f->f
								   		  .loginPage("/login")
								   		  .loginProcessingUrl("/mylogin")   //Tell Spring Security which POST URL should be used for login requests.
								   		  .defaultSuccessUrl("/product")
								   		  .failureUrl("/login?error=true"));
		
		return http.build();
	}
}
