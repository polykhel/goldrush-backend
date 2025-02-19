package com.goldrush.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
public class AuditorAwareConfig {

  @Bean
  public AuditorAware<String> auditorProvider() {
    return () -> {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

      if (authentication == null || !authentication.isAuthenticated()) {
        return Optional.empty();
      }

      if (authentication.getPrincipal() instanceof UsernamePasswordAuthenticationToken authToken) {
        return Optional.of(authToken.getName());
      }

      if (authentication.getPrincipal() instanceof String principal) {
        return Optional.of(principal);
      }

      return Optional.empty();
    };
  }
}
