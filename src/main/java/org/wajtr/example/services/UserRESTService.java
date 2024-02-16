package org.wajtr.example.services;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * A demo REST service.
 */
@Component
public class UserRESTService {
    /**
     * @param username
     * @param password
     * @return
     * @throws BadCredentialsException on incorrect password
     * @throws
     */
    public User loginUser(String username, String password) throws AuthenticationException {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        final MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
        if (username.equals("user")) {
            if (!password.equals("user")) {
                throw new BadCredentialsException(messages
                        .getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
            }
            return new User(username, "IGNORED", List.of(new SimpleGrantedAuthority("ROLE_USER")));
        }
        if (username.equals("admin")) {
            if (!password.equals("admin")) {
                throw new BadCredentialsException(messages
                        .getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
            }
            return new User(username, "IGNORED", List.of(new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ADMIN")));
        }
        throw new UsernameNotFoundException("No user present with username: " + username);
    }
}
