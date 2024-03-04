package org.wajtr.example.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.wajtr.example.AbstractAppTest;

import static org.junit.jupiter.api.Assertions.*;

public class SecurityServiceTest extends AbstractAppTest {
    @Test
    public void byDefaultNoUserLoggedIn() {
        assertNull(SecurityService.getAuthenticatedUser());
        assertFalse(SecurityService.isLoggedIn());
    }

    @Test
    public void fakeLogin() {
        loginUser();
        UserDetails user = SecurityService.getAuthenticatedUser();
        assertNotNull(user);
        assertEquals("user", user.getUsername());
        loginAdmin();
        user = SecurityService.getAuthenticatedUser();
        assertNotNull(user);
        assertEquals("admin", user.getUsername());
    }

    @Test
    public void logoutLogsFakeUserOut() {
        loginUser();
        assertTrue(SecurityService.isLoggedIn());
        logout();
        assertFalse(SecurityService.isLoggedIn());
        assertNull(SecurityService.getAuthenticatedUser());
    }
}
