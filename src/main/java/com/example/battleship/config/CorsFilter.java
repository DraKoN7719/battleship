package com.example.battleship.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class CorsFilter {

    @Bean
    public WebMvcConfigurer configure() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Соединение с фронтом
                registry.addMapping("/api/**")
                        .maxAge(3600)
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("Origin", "Access-Control-Allow-Origin", "Content-Type", "Accept",
                                "Authorization", "Origin, Accept", "X-Requested-With",
                                "Access-Control-Request-Method", "Access-Control-Request-Headers")
                        .exposedHeaders("Origin", "Total-Count", "Content-Type", "Accept", "Authorization",
                                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin",
                                "Access-Control-Allow-Credentials")
                        .allowCredentials(true);
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*");
                WebMvcConfigurer.super.addCorsMappings(registry);
            }

        };
    }
}