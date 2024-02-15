package edu.scranton.cs.se518.nile.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.authorization.AuthorizationManagers;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import edu.scranton.cs.se518.nile.security.OrderAuthorizationManager;

/**
 * As the name implies, SecurityConfiguration is the heart of the Spring
 * Security configuration for this project and where you are going to be making
 * your changes to enforce authorization on the Spring REST endpoints.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	OrderAuthorizationManager orderAuthorizationManager;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		/*
		 * Here we configure HTTP Basic Authentication to require a username and
		 * password. Any standard tool for making HTTP requests will support Basic
		 * Authentication. Using the old standby cURL program, for example, this is done
		 * with the --user flag.
		 */

		http.httpBasic(Customizer.withDefaults());

		/*
		 * We additionally disable CSRF, a feature for preventing users from being
		 * tricked into making malicious POST requests in the browser due to carelessly
		 * clicking on untrusted links. Disabling CSRF is purely a convenience to
		 * simplify working on this assignment - you should be very hesitant to disable
		 * CSRF in a real application.
		 */
		http.csrf(customizer -> customizer.disable());

		http.authorizeHttpRequests(authorize -> {
			/*
			 * TODO(student) This is where you need to implement the authorization logic.
			 * The initial logic I've configured allows every authenticated user to do
			 * everything. This is clearly wrong, and your task is to bring things here into
			 * alignment with the requirements defined in the data.sql file. You should be
			 * able to accomplish this goal using only methods on the authorize object
			 * provided here and should not write any code outside of this
			 * authorizeHttpRequests() lambda expression.
			 */
			authorize.requestMatchers(HttpMethod.GET, "/widgets", "/widgets/*").permitAll()
					.requestMatchers(HttpMethod.DELETE, "/widgets/**").hasRole("ADMINISTRATOR")
					.requestMatchers(HttpMethod.POST, "/widgets").hasAnyAuthority("LIST_WIDGET", "ROLE_ADMINISTRATOR")
					.requestMatchers(HttpMethod.POST, "/orders").authenticated()
					.requestMatchers(HttpMethod.DELETE, "/orders/**")
					.hasAnyAuthority("CANCEL_CUSTOMER_ORDER", "ROLE_ADMINISTRATOR")
					.requestMatchers(HttpMethod.GET, "/orders/{id}")
					.access(AuthorizationManagers.anyOf(orderAuthorizationManager,
							AuthorityAuthorizationManager.hasAnyAuthority("VIEW_CUSTOMER_ORDER", "ROLE_ADMINISTRATOR")))
					.anyRequest().denyAll();
		});
		return http.build();
	}
}
