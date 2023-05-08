package org.eauction.application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
@ComponentScan(basePackages = "org.eauction")
@EnableJpaRepositories(basePackages = "org.eauction.repos",entityManagerFactoryRef = "pocEntityMgrFactory")
@EnableAutoConfiguration
@EntityScan(basePackages = "org.eauction.entity")
@ConfigurationPropertiesScan("org.eauction.kafka")
@EnableEurekaClient
public class EAuctionApplication implements CommandLineRunner{

	
	public static void main(String[] args) {
		
		SpringApplication.run(EAuctionApplication.class, args);
		
	}

	@Override
    public void run(String... args) throws Exception {
    }

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder() ;
	}
	
}
