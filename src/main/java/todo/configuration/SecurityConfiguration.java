package todo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by bill.villaflor on 6/29/16.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    @Autowired
    public void setUserDetailsService( UserDetailsService userDetailsService ){

        this.userDetailsService = userDetailsService;
    }

    @Override
    protected  void configure( HttpSecurity http ) throws Exception {

        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                    .formLogin()
                    .loginPage( "/login" )
                    .permitAll()
                .and()
                    .logout()
                    .permitAll()
                .and()
                    .csrf()
                    .disable();
    }

    @Autowired
    public void configureGlobal( AuthenticationManagerBuilder auth ) throws Exception {

        auth.userDetailsService(userDetailsService);
    }
}
