package com.abc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThymeleafWithSecurityApplication {

	//JIRA-102
	public void m2(){
	}

	//JIRA-101
	public void m1(){
		int i=10;
		String name="abc";
	}

	public static void main(String[] args) {
		SpringApplication.run(ThymeleafWithSecurityApplication.class, args);
		int i = 10;
	}

}
