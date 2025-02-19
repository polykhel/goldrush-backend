package com.goldrush.api.web;

import com.goldrush.api.dto.request.AuthRequest;
import com.goldrush.api.dto.request.SignupRequest;
import com.goldrush.api.dto.UserDto;
import com.goldrush.api.security.JwtService;
import com.goldrush.api.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthResource {

  private final JwtService jwtService;
  private final UserService userService;
  private final AuthenticationManager authenticationManager;
  
  public AuthResource(JwtService jwtService, UserService userService, AuthenticationManager authenticationManager) {
    this.jwtService = jwtService;
    this.userService = userService;
    this.authenticationManager = authenticationManager;
  }

  /**
   * Registers a new user based on the provided signup request details.
   *
   * @param request the {@link SignupRequest} containing the user's email, password, and name.
   * @return a {@link ResponseEntity} containing the {@link UserDto} for the newly created user.
   */
  @PostMapping("/signup")
  public ResponseEntity<UserDto> signup(@Valid @RequestBody SignupRequest request) {
    return ResponseEntity.ok(userService.signup(request));
  }

  /**
   * Authenticates a user based on the provided email and password in the request body
   * and generates a JWT token for the authenticated user.
   *
   * @param request the {@link AuthRequest} containing the user's email and password.
   * @return a {@link ResponseEntity} containing the generated JWT token as a string.
   */
  @PostMapping("/login")
  public ResponseEntity<String> login(@Valid @RequestBody AuthRequest request) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    String token = jwtService.generateToken(userDetails);
    return ResponseEntity.ok(token);
  }

  @GetMapping("/me")
  public UserDto getCurrentUser(Principal principal) {
    return userService.getByEmail(principal.getName());
  }

  @GetMapping("/admins")
  @Secured("ROLE_ADMIN")
  public String adminsOnly() {
    return "Admins can see this!";
  }

  @GetMapping("/admins-and-users")
  @Secured({"ROLE_ADMIN", "ROLE_USER"})
  public String adminsAndUsers() {
    return "Admins and users can see this!";
  }

  @GetMapping("/users")
  @Secured("ROLE_USER")
  public String usersOnly() {
    return "Users can see this!";
  }
}
