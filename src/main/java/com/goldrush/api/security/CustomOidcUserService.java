package com.goldrush.api.security;

import com.goldrush.api.domain.User;
import com.goldrush.api.repository.UserRepository;
import java.util.Optional;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
public class CustomOidcUserService extends OidcUserService {

  private final UserRepository userRepository;

  public CustomOidcUserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
    OidcUser oidcUser = super.loadUser(userRequest);

    String googleId = oidcUser.getSubject();
    String email = oidcUser.getEmail();
    String name = oidcUser.getFullName();
    String profileImage = oidcUser.getPicture();

    Optional<User> optionalUser = userRepository.findByGoogleId(googleId);

    if (optionalUser.isEmpty()) {
      User user = new User();
      user.setGoogleId(googleId);
      user.setEmail(email);
      user.setName(name);
      user.setProfileImage(profileImage);
      user.setRole("ROLE_USER");

      userRepository.save(user);
    }

    return new DefaultOidcUser(
        oidcUser.getAuthorities(), oidcUser.getIdToken(), oidcUser.getUserInfo());
  }
}
