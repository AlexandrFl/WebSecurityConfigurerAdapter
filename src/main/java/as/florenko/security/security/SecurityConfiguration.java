package as.florenko.security.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder a) throws Exception {
        a.inMemoryAuthentication()
                .withUser("User").password("{noop}qwerty").authorities("age check")
                .and()
                .withUser("Qwer").password("{noop}asdfg").authorities("name check");

    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin()
                .and()
                .authorizeRequests().antMatchers("/persons/by-city").permitAll()
                .and()
                .authorizeRequests().antMatchers("/persons/by-age").hasAuthority("age check")
                .and()
                .authorizeRequests().antMatchers("/persons/by-name").hasAuthority("name check")
                .and()
                .authorizeRequests().anyRequest().authenticated();
    }
}
