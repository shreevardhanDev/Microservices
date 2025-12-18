package io.javabrains.springsecurityjwt;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.javabrains.springsecurityjwt.filters.JwtRequestFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Bean
	protected PasswordEncoder createDelegatingPasswordEncoder() {
		// //update users set password =
		// '{bcrypt}$2a$10$NOtvXNdcmPymn6iSJSBdmefLnFADCu0GeMMCSBRkCa7W1Mn7z5vGq' where
		// id=1
		// //encode password to a encoded code and prepend{bcrypt} to it as above. that
		// will need to be saved in database
		// BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		// System.out.println(encoder.encode("shree"));
		//here both user name and password are shree
		String idForEncode = "bcrypt";
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put(idForEncode, new BCryptPasswordEncoder());
		return new DelegatingPasswordEncoder(idForEncode, encoders);
	}

	@Bean
	protected AuthenticationManager getAuthenticationManager(HttpSecurity http, UserDetailsService userDetailsService)
			throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(userDetailsService)
				.passwordEncoder(createDelegatingPasswordEncoder());

		return authenticationManagerBuilder.build();
	}

	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager)
			throws Exception {

		http.csrf((csrf) -> csrf.disable())
				.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
				.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests.requestMatchers("/authenticate")
						.permitAll())
				.authenticationManager(authenticationManager)
				.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return http.build();
	}

}
