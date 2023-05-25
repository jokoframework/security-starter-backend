package io.github.jokoframework.securitystarterbackend;

import io.github.jokoframework.security.springex.JokoWebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import io.github.jokoframework.securitystarterbackend.config.BackendSecurityConfig;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@ComponentScan(basePackages = {"io.github.jokoframework"})
@EnableJpaRepositories(basePackages = {"io.github.jokoframework"})
@EntityScan(basePackages = {"io.github.jokoframework"})
public class SecurityStarterBackendApplication {

	public static void main(String[] args) {

		SpringApplication.run(SecurityStarterBackendApplication.class, args);
	}

	/*@Bean
	public WebSecurityConfigurerAdapter webSecurityConfigurerAdapter() {
		return new BackendSecurityConfig();
	}*/

}
