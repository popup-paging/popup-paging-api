package common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
		                .allowedOrigins(
	                        "http://localhost:8080",           // 로컬 개발용
	                        "http://52.78.23.52:8080"       // ec2 서버용
	                    )
                        .allowedMethods("*")
                        .allowCredentials(true); // ← 쿠키 허용
            }
        };
    }
}