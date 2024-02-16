package org.wajtr.example.security;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.wajtr.example.services.UserRESTService;

// We still extend AbstractUserDetailsAuthenticationProvider to benefit most of the functionality of user and password checking
@Component
public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private UserRESTService userService;

    @Override
    protected void additionalAuthenticationChecks(@NotNull UserDetails userDetails, @NotNull UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    }

    @Override
    @NotNull
    protected UserDetails retrieveUser(@NotNull String username, @NotNull UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        // just some basic checking first
        if (authentication.getCredentials() == null) {
            this.logger.debug("Failed to authenticate since no credentials provided");
            throw new BadCredentialsException(this.messages
                    .getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }
        String presentedPassword = authentication.getCredentials().toString();
        return userService.loginUser(username, presentedPassword);
    }
}
