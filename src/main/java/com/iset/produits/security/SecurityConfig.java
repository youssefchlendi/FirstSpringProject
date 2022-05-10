package com.iset.produits.security;

import java.io.IOException;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iset.produits.dao.AppAuthProvider;
import com.iset.produits.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String AUTHORITIES_CLAIM_NAME = "roles";
    @Autowired
    UserService userDetailsService;
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling().accessDeniedPage("/accessDenied");
        http.csrf().disable()
                .authenticationProvider(getProvider())
                .formLogin().loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(new AuthentificationLoginSuccessHandler())
                .failureHandler(new AuthentificationLoginErrorHandler())
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new AuthentificationLogoutSuccessHandler())
                .invalidateHttpSession(true)
                .and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/logout").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/loggedout").permitAll()
                // .hasAnyRole("USER", "ADMIN")
                .antMatchers("/showCreate", "/saveProduit").hasAnyRole("ADMIN", "AGENT")
                .antMatchers("/ListeProduits")
                .hasAnyRole("ADMIN", "AGENT", "USER")
                .antMatchers("/supprimerProduit", "/modifierProduit", "/updateProduit")
                .hasAnyRole("ADMIN")
                .anyRequest().authenticated();

    }

    private class AuthentificationLoginSuccessHandler extends
            SimpleUrlAuthenticationSuccessHandler {
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                Authentication authentication) throws IOException, ServletException {
            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect("/produits/ListeProduits");
        }
    }

    private class AuthentificationLoginErrorHandler extends
            SimpleUrlAuthenticationFailureHandler {
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                org.springframework.security.core.AuthenticationException exception)
                throws IOException, ServletException {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            System.out.println(request.toString());
            response.sendRedirect("/produits/login?error=1");
        }

    }

    private class AuthentificationLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
        @Override
        public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                Authentication authentication) throws IOException, ServletException {
            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect("/produits/login?logout=1");
        }
    }

    @Bean
    public AuthenticationProvider getProvider() {
        AppAuthProvider provider = new AppAuthProvider();
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedHandlerImpl();
    }

}
