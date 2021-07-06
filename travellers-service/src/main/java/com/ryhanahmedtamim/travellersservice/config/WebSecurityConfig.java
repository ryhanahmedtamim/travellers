package com.ryhanahmedtamim.travellersservice.config;

import com.ryhanahmedtamim.travellersservice.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${user-admin}")
    private String adminUsername;
    @Value("${password-admin}")
    private String adminPassword;

    @Value("${role-admin}")
    private String adminRole;

    @Value("${user-test}")
    private String testUserUsername;
    @Value("${password-test-user}")
    private String testUserPassword;

    @Value("${role-usr}")
    private String testUserRole;

    @Autowired
    DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/bootstrap/**", "/dist/**", "/plugins/**").permitAll()
                .antMatchers("/adminPage").access("hasRole('ADMIN')")
                .antMatchers("/testUser").access("hasRole('ADMIN') or hasRole('USER')")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .failureUrl("/login?error")
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .permitAll();
    }



//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////        //Use Spring Boots User detailsMAnager
//        JdbcUserDetailsManager userDetailsService = userDetailsService();
////
//       //Set our Datasource to use the one defined in application.properties
//        userDetailsService.setDataSource(dataSource);
////
////        //Create BCryptPassword encoder
//        PasswordEncoder encoder = passwordEncoder();
//
//        //add components
//        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
//        auth.jdbcAuthentication().dataSource(dataSource);
//
////        auth.inMemoryAuthentication()
////                .withUser(adminUsername).password("{noop}"+ adminPassword).roles(adminRole)
////        .and().withUser(testUserUsername).password("{noop}"+testUserPassword).roles(testUserRole);
//
//        // add new user "user" with password "password" - password will be encrypted
////        if (!userDetailsService.userExists("ekyc")) {
////            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
////            authorities.add(new SimpleGrantedAuthority("USER"));
////            User userDetails = new User("ekyc", encoder.encode("1234"), authorities);
////            userDetailsService.createUser(userDetails);
////        }
//    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/user-register-form", "/add-new-user");
    }

}
