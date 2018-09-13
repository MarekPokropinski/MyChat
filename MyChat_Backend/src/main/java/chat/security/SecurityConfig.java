package chat.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests()
				.antMatchers("/login", "/logout", "/user/register", "/user/empty", "/user/all").permitAll().anyRequest()
				.authenticated().and().logout().permitAll().and().csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS);
	}

	@Autowired
	public void configureAuthenticationManagerBuilder(AuthenticationManagerBuilder authenticationManagerBuilder)
			throws Exception {
//		authenticationManagerBuilder.inMemoryAuthentication().withUser("marek").password("123").authorities("USER");
		authenticationManagerBuilder.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select u.username, u.password, u.enabled from users u where u.username = ?")
				.authoritiesByUsernameQuery("select username, authority from authorities where username = ?")
				.passwordEncoder(passwordEncoder);
	}
}