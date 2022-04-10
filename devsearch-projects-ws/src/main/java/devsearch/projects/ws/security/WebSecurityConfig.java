package devsearch.projects.ws.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

	JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
	// jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new
	// KeycloakRoleConverter()); // delegate to custom
	// converter - not needed for now

	http.csrf()
		.disable() // Disable CSRF is needed for POST requests!!!
		.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/projects/status")
		.permitAll()
		.antMatchers(HttpMethod.GET, "/projects/public/all")
		.permitAll()
		.antMatchers(HttpMethod.GET, "/projects/public/project/*")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.oauth2ResourceServer()
		.jwt()
		.jwtAuthenticationConverter(jwtAuthenticationConverter); // apply jwt converter;

	http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
