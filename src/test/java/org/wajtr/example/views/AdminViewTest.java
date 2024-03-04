package org.wajtr.example.views;

import com.vaadin.flow.component.UI;
import org.junit.jupiter.api.Test;
import org.wajtr.example.AbstractAppTest;
import org.wajtr.example.views.login.LoginView;

import static com.github.mvysny.kaributesting.v10.LocatorJ._assertOne;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AdminViewTest extends AbstractAppTest {
    @Test
    public void inaccessibleByAnonymous() {
        UI.getCurrent().navigate(AdminView.class);
        _assertOne(LoginView.class);
    }

    @Test
    public void inaccessibleByUser() {
        loginUser();
        assertThrows(RuntimeException.class, () -> {
            UI.getCurrent().navigate(AdminView.class);
        });
        _assertOne(LoginView.class);
    }

    @Test
    public void accessibleByAdmin() {
        loginAdmin();
        UI.getCurrent().navigate(AdminView.class);
        _assertOne(AdminView.class);
    }
}
