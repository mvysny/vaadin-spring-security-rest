package org.wajtr.example.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.vaadin.lineawesome.LineAwesomeIcon;
import org.wajtr.example.security.SecurityService;
import org.wajtr.example.views.about.AboutView;
import org.wajtr.example.views.helloworld.HelloWorldView;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    @NotNull
    private final H2 viewTitle = new H2();

    @NotNull
    private final AccessAnnotationChecker accessChecker;

    public MainLayout(@NotNull AccessAnnotationChecker accessChecker) {
        this.accessChecker = accessChecker;

        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("BasicWithLogin");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    @NotNull
    private SideNav createNavigation() {
        SideNav nav = new SideNav();

        if (accessChecker.hasAccess(HelloWorldView.class)) {
            nav.addItem(new SideNavItem("Hello World", HelloWorldView.class, LineAwesomeIcon.GLOBE_SOLID.create()));
        }
        if (accessChecker.hasAccess(AboutView.class)) {
            nav.addItem(new SideNavItem("About", AboutView.class, LineAwesomeIcon.FILE.create()));
        }
        if (accessChecker.hasAccess(AdminView.class)) {
            nav.addItem(new SideNavItem("Admin", AdminView.class, LineAwesomeIcon.COG_SOLID.create()));
        }

        return nav;
    }

    @NotNull
    private Footer createFooter() {
        Footer layout = new Footer();

        UserDetails user = SecurityService.getAuthenticatedUser();
        if (user != null) {

            Avatar avatar = new Avatar(user.getUsername());
//            StreamResource resource = new StreamResource("profile-pic",
//                    () -> new ByteArrayInputStream(user.getProfilePicture()));
//            avatar.setImageResource(resource);
            avatar.setThemeName("xsmall");
            avatar.getElement().setAttribute("tabindex", "-1");

            MenuBar userMenu = new MenuBar();
            userMenu.setThemeName("tertiary-inline contrast");

            MenuItem userName = userMenu.addItem("");
            Div div = new Div();
            div.add(avatar);
            div.add(user.getUsername());
            div.add(new Icon("lumo", "dropdown"));
            div.getElement().getStyle().set("display", "flex");
            div.getElement().getStyle().set("align-items", "center");
            div.getElement().getStyle().set("gap", "var(--lumo-space-s)");
            userName.add(div);
            userName.getSubMenu().addItem("Sign out", e -> SecurityService.logout());

            layout.add(userMenu);
        } else {
            Anchor loginLink = new Anchor("login", "Sign in");
            layout.add(loginLink);
        }

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    @NotNull
    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
