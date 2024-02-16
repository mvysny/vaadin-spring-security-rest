## How to implement custom username and password validation using Spring Security and Vaadin

I used https://start.vaadin.com to create a base for this project. I configured two views that are accessible 
only when the user logs in. This is the [link to the project](https://start.vaadin.com/app/p?id=d44c0733-7335-4e7a-bceb-287ebd20e863&preview=)
That added Spring Security to the project (see `SecurityConfiguration` class) and did the necessary configuration
by extending the `VaadinWebSecurity`. That makes things much easier.

This default configuration uses the `UserRESTService` to login the user.
`UserRESTService` has only one method: `loginUser(String username, String password)`.

If, for any reason, you do not like this flow, or you simply need to validate the password using another way,
you have to provide your own implementation of the `AuthenticationProvider`. See `CustomAuthenticationProvider` class for more details.

> Acknowledgements: this app builds on excellent [Bretislav Wajtr's Custom Password Check app](https://github.com/bwajtr/example-vaadin-spring-security-custom-password-check).

## Useful stuff for debugging

* Place a breakpoint in the `org.springframework.security.authentication.ProviderManager.authenticate` method and see what is going on in there when you press Login button in the login form
* Place a breakpoint in the `UserRESTService.loginUser()` method and see what is going on in there when you press Login button in the login form
* Spring Security uses a set of filters to perform the whole authentication process. Take a look what filters and in which order they are executed by searching for `DefaultSecurityFilterChain` in the application log. The log line starts with `Will securty any request with`

## Running the application

The project is a standard Maven project. To run it from the command line,
type `mvnw` (Windows), or `./mvnw` (Mac & Linux), then open
http://localhost:8080 in your browser.

You can also import the project to your IDE of choice as you would with any
Maven project. Read more on [how to import Vaadin projects to different IDEs](https://vaadin.com/docs/latest/guide/step-by-step/importing) (Eclipse, IntelliJ IDEA, NetBeans, and VS Code).
