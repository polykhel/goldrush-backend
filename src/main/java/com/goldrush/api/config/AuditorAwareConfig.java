package com.goldrush.api.config;

import com.goldrush.api.domain.User;
import com.goldrush.api.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.Optional;

@Configuration
public class AuditorAwareConfig {

  private final UserRepository userRepository;

  public AuditorAwareConfig(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Bean
  public AuditorAware<String> auditorProvider() {
    return () -> {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

      if (authentication == null || !authentication.isAuthenticated()){
        return Optional.empty();
      }

      if (authentication.getPrincipal() instanceof OidcUser oidcUser){
        String googleId = oidcUser.getSubject();
        return userRepository.findByGoogleId(googleId).map(User::getName);
      }

      if (authentication.getPrincipal() instanceof String principal){
        return Optional.of(principal);
      }

      return Optional.empty();
    };
  }
}
