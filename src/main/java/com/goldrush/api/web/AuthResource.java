package com.goldrush.api.web;

import com.goldrush.api.security.JwtUtil;
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

  public AuthResource(JwtUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  @GetMapping("/callback")
  public Map<String, Object> callback(@RequestParam("token") String token) {
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
  public Map<String, Object> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
    String token = authHeader.replace("Bearer ", "");

    // Extract claims from the JWT
    String username = jwtUtil.getUsernameFromToken(token);
    String email = jwtUtil.getClaimFromToken(token, claims -> claims.get("email", String.class));
    String name = jwtUtil.getClaimFromToken(token, claims -> claims.get("name", String.class));
    String picture =
        jwtUtil.getClaimFromToken(token, claims -> claims.get("picture", String.class));

    // Return user details
    Map<String, Object> userDetails = new HashMap<>();
    userDetails.put("username", username);
    userDetails.put("email", email);
    userDetails.put("name", name);
    userDetails.put("picture", picture);

    return userDetails;
  }
}
