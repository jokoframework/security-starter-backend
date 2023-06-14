package io.github.jokoframework.securitystarterbackend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"io.github.jokoframework"})
@EnableJpaRepositories(basePackages = {"io.github.jokoframework"})
@EntityScan(basePackages = {"io.github.jokoframework"})
public class SecurityStarterBackendApplication {

	public static void main(String[] args) {

		SpringApplication.run(SecurityStarterBackendApplication.class, args);
	}
}
