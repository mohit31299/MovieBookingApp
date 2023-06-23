package com.cognizant.MovieBookingApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@SpringBootApplication
public class MovieBookingApplication {
	
	@Configuration
	class openApiConfig{
		
		@Bean
		public OpenAPI customConfig(){
			
			final String securitySchemeName = "bearerAuth";
			
			return new OpenAPI()
					            .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
					            .components(new Components().addSecuritySchemes(securitySchemeName, 
					            		new SecurityScheme()
					            		.name(securitySchemeName)
					            		.type(SecurityScheme.Type.HTTP)
					            		.scheme("bearer")
					            		.bearerFormat("jwt")));
					            
			
		}
	}
	

	public static void main(String[] args) {
		SpringApplication.run(MovieBookingApplication.class, args);
	}

}
