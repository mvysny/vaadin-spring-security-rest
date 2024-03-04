package org.wajtr.example.views.helloworld;

import com.vaadin.flow.component.UI;
import org.junit.jupiter.api.Test;
import org.wajtr.example.AbstractAppTest;
import org.wajtr.example.views.login.LoginView;

import static com.github.mvysny.kaributesting.v10.LocatorJ._assertOne;

public class HelloWorldViewTest extends AbstractAppTest {
    @Test
    public void inaccessibleByAnonymous() {
        UI.getCurrent().navigate(HelloWorldView.class);
        _assertOne(LoginView.class);
    }

    @Test
    public void accessibleByUser() {
        loginUser();
        UI.getCurrent().navigate(HelloWorldView.class);
        _assertOne(HelloWorldView.class);
    }

    @Test
    public void accessibleByAdmin() {
        loginAdmin();
        UI.getCurrent().navigate(HelloWorldView.class);
        _assertOne(HelloWorldView.class);
    }
}
