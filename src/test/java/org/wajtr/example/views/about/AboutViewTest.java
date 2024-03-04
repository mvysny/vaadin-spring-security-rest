package org.wajtr.example.views.about;

import com.vaadin.flow.component.UI;
import org.junit.jupiter.api.Test;
import org.wajtr.example.AbstractAppTest;

import static com.github.mvysny.kaributesting.v10.LocatorJ._assertOne;

public class AboutViewTest extends AbstractAppTest {
    @Test
    public void accessibleByAnonymousAsWell() {
        UI.getCurrent().navigate(AboutView.class);
        _assertOne(AboutView.class);
    }

    @Test
    public void accessibleByUser() {
        loginUser();
        UI.getCurrent().navigate(AboutView.class);
        _assertOne(AboutView.class);
    }

    @Test
    public void accessibleByAdmin() {
        loginAdmin();
        UI.getCurrent().navigate(AboutView.class);
        _assertOne(AboutView.class);
    }
}
