package ma.enset.comptebancaire.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.Md4PasswordEncoder;

import javax.sql.DataSource;

import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*
        auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}1234").roles("ADMIN","USER");
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}1234").roles("USER");
*/
        auth.jdbcAuthentication()
                .dataSource(dataSource).
                usersByUsernameQuery("select username as principal , password as credentials, active from user where username = ?")
                .authoritiesByUsernameQuery("select username as principal , roles as role from users_roles where username=?")
                .rolePrefix("ROLE_").passwordEncoder(new BCryptPasswordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login");

        http.authorizeRequests().antMatchers("/operations","/consultercompte").hasRole("USER");
        http.authorizeRequests().antMatchers("/saveOperation").hasRole("ADMIN ");
        http.exceptionHandling().accessDeniedPage("/403");
    }
}
