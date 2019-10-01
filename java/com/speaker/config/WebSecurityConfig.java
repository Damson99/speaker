package com.speaker.config;

import com.speaker.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private DataSource dataSource;

    @Autowired
    private RoleService roleService;

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/userProfile/**", "/chat", "/userInvitedYou/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/usersAdminList/**", "/chat/user/**", "/userProfile/user/**").hasAnyAuthority("ADMIN")
                .and()
                .formLogin()./*loginPage("/login").*/defaultSuccessUrl("/");

    }

    @Bean
    public SessionRegistry sessionRegistry()
    {
        return new SessionRegistryImpl();
    }

    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher()
    {
        return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception
    {
        return authenticationManager();
    }

    @Autowired
    public void securityUsers(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder()).dataSource(dataSource).
                usersByUsernameQuery("SELECT email, password, enabled FROM User WHERE email = ?")
                .authoritiesByUsernameQuery("SELECT email, role FROM ROLE WHERE email = ?");
    }

    public boolean isUserLogged()
    {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails;
    }

    public String getLoggedUserEmail()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user.getUsername();
    }

    public Object userPrivileges()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object privileges = authentication.getAuthorities();
        return privileges.toString();
    }
}
