package de.gts.redrail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class RedRailIdleGameApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(RedRailIdleGameApplication.class, args);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("http://localhost:4200") // or your frontend URL
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
	}

}
