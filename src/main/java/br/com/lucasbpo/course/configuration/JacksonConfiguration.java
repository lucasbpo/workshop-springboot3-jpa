package br.com.lucasbpo.course.configuration;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfiguration {

        @Bean
        public JavaTimeModule javaTimeModule() {
            return new JavaTimeModule();
        }
}
