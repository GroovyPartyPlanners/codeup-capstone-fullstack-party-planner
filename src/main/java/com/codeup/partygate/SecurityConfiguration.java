package com.codeup.partygate;

<<<<<<< HEAD
import com.codeup.partygate.controller.WebSecurityConfigurerAdapter;
import com.codeup.partygate.model.UserDetailsLoader;
=======
import com.codeup.partygate.services.UserDetailsLoader;
>>>>>>> 54dc1e7653e7afc1e7b121e3cdbbb137299536f0
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
<<<<<<< HEAD
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

=======
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
>>>>>>> 54dc1e7653e7afc1e7b121e3cdbbb137299536f0

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

<<<<<<< HEAD
    private UserDetailsLoader usersLoader;
=======
    private final UserDetailsLoader usersLoader;
>>>>>>> 54dc1e7653e7afc1e7b121e3cdbbb137299536f0

    public SecurityConfiguration(UserDetailsLoader usersLoader) {
        this.usersLoader = usersLoader;
    }

    @Bean
<<<<<<< HEAD
    public BCryptPasswordEncoder passwordEncoder() {
=======
    public PasswordEncoder passwordEncoder() {
>>>>>>> 54dc1e7653e7afc1e7b121e3cdbbb137299536f0
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
<<<<<<< HEAD
        auth
                .userDetailsService(usersLoader) // How to find users by their username
=======
        auth.userDetailsService(usersLoader) // How to find users by their username
>>>>>>> 54dc1e7653e7afc1e7b121e3cdbbb137299536f0
                .passwordEncoder(passwordEncoder()) // How to encode and verify passwords
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
<<<<<<< HEAD
        http
                /* Login configuration */
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/ads") // user's home page, it can be any URL
=======
        /* Login configuration */
        http.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/") // user's home page, it can be any URL
>>>>>>> 54dc1e7653e7afc1e7b121e3cdbbb137299536f0
                .permitAll() // Anyone can go to the login page
                /* Logout configuration */
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout") // append a query string value
                /* Pages that can be viewed without having to log in */
                .and()
                .authorizeRequests()
<<<<<<< HEAD
                .antMatchers("/", "/ads") // anyone can see the home and the ads pages
=======
                .antMatchers("/", "/sign-up", "/about") // anyone can see the home page("/"), the sign-up page("/sign-up"), and the about us page("/about")
>>>>>>> 54dc1e7653e7afc1e7b121e3cdbbb137299536f0
                .permitAll()
                /* Pages that require authentication */
                .and()
                .authorizeRequests()
<<<<<<< HEAD
                .antMatchers(
                        "/ads/create", // only authenticated users can create ads
                        "/ads/{id}/edit" // only authenticated users can edit ads
                )
=======
                .antMatchers("/*") // only authenticated users can access every page
>>>>>>> 54dc1e7653e7afc1e7b121e3cdbbb137299536f0
                .authenticated()
        ;
    }
}
