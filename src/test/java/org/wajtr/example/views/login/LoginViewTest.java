package org.wajtr.example.views.login;

import com.vaadin.flow.component.UI;
import org.junit.jupiter.api.Test;
import org.wajtr.example.views.AbstractAppTest;
import org.wajtr.example.views.helloworld.HelloWorldView;

import static com.github.mvysny.kaributesting.v10.LocatorJ._assertOne;

public class LoginViewTest extends AbstractAppTest {
    @Test
    public void smokeTest() {
        UI.getCurrent().navigate(LoginView.class);
        _assertOne(LoginView.class);
    }

    @Test
    public void securityRedirectWorks() {
        UI.getCurrent().navigate(HelloWorldView.class);
        _assertOne(LoginView.class);
    }
}
