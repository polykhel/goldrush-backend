package com.goldrush.api.web;


import com.goldrush.api.model.User;
import com.goldrush.api.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@Slf4j
public class HomeResource {

  private final UserRepository userRepository;

  public HomeResource(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/")
  public String home(@AuthenticationPrincipal Principal principal) {
    if (principal != null) {

      String name = principal.getName();
      User user = userRepository.findByEmail(name).orElseThrow();

      log.info(user.getEmail());
      log.info(user.getName());
    }

    return "Hello World!";
  }
}
