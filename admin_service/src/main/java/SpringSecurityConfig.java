
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration

@EnableWebSecurity

// Enabling spring security by extending WebSecurityConfigurerAdapter class

public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("pwd")).roles("ADMIN").and()
				.withUser("user").password(passwordEncoder().encode("pwd")).roles("USER");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override

	protected void configure(HttpSecurity http) throws Exception {

		// http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();

		http.csrf().disable().httpBasic().and().authorizeRequests().antMatchers("/authenticate")
				.hasAnyRole("USER", "ADMIN").anyRequest().authenticated().and()
				.addFilter(new JwtAuthorizationFilter(authenticationManager()));
	}
}

// authentication is done using the following username and password

// @Override
//
// protected void configure(AuthenticationManagerBuilder auth) throws Exception
// {
//
// auth.inMemoryAuthentication().withUser("abc").password("{noop}abc").roles("USER");
//
// }
//
// }
//
