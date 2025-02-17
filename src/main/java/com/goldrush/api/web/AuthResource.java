package com.goldrush.api.web;

import com.goldrush.api.dto.UserDto;
import com.goldrush.api.security.JwtUtil;
import com.goldrush.api.service.UserService;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthResource {

  private final JwtUtil jwtUtil;
  private final UserService userService;

  public AuthResource(JwtUtil jwtUtil, UserService userService) {
    this.jwtUtil = jwtUtil;
    this.userService = userService;
  }

  @GetMapping("/callback")
  public Map<String, Object> callback(@RequestParam("access_token") String token) {
    Map<String, Object> response = new HashMap<>();

    if (jwtUtil.validateToken(token)) {
      Date expiration = jwtUtil.getExpirationFromToken(token);

      response.put("expiration", expiration);
      response.put("token", token);
    } else {
      response.put("error", "Invalid token");
    }

    return response;
  }

  @GetMapping("/me")
  public UserDto getCurrentUser(@RequestHeader("Authorization") String authHeader) {
    String token = authHeader.replace("Bearer ", "");
    String username = jwtUtil.getUsernameFromToken(token);
    return userService.getByEmail(username);
  }
}
