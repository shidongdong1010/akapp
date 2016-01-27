package com.akcome.akapp;

import com.akcome.akapp.domain.User;
import com.akcome.akapp.service.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableAutoConfiguration
public class AkappApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AkappApplication.class);
	}

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(AkappApplication.class, args);

		/*UserServiceImpl userService = ctx.getBean(UserServiceImpl.class);
		User su= userService.findUserById(1L);
		BCryptPasswordEncoder bc=new BCryptPasswordEncoder(4);
		su.setPassword(bc.encode("111111"));
		userService.update(su);*/

	}
}
