package com.goldrush.api.web;


import com.goldrush.api.domain.User;
import com.goldrush.api.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HomeResource {

  private final UserRepository userRepository;

  public HomeResource(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/")
  public String home(@AuthenticationPrincipal OidcUser oidcUser) {
    if (oidcUser != null) {

      String googleId = oidcUser.getSubject();
      User user = userRepository.findByGoogleId(googleId).orElseThrow();

      log.info(user.getEmail());
      log.info(user.getName());
    }

    return "Hello World!";
  }
}
