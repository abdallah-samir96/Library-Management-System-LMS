package com.lms.app.config;


import com.lms.app.config.properties.BlobProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Optional;

@Configuration
@EnableWebSecurity
@EnableJpaAuditing
@EnableConfigurationProperties(BlobProperties.class)
public class ApplicationConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .build();
    }

    /**
     * This method used for the JPA Auditing updatedBy, createdBy
     *
     */
    @Bean
    public AuditorAware<String> auditorAware() {

        // getting the security context, principal and get user

        return ()-> Optional.of("by_system for now");

    }
}
