package com.iset.produits.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = passwordEncoder ();
        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("admin")).roles("ADMIN");
        auth.inMemoryAuthentication().withUser("youssef").password(passwordEncoder.encode("youssef")).roles("AGENT", "USER");
        auth.inMemoryAuthentication().withUser("user").password(passwordEncoder.encode("user")).roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/showCreate", "/saveProduit").hasAnyRole("ADMIN", "AGENT");
        http.authorizeRequests().antMatchers("/ListeProduits")
                .hasAnyRole("ADMIN", "AGENT", "USER");
        http.authorizeRequests()
                .antMatchers("/supprimerProduit", "/modifierProduit", "/updateProduit")
                .hasAnyRole("ADMIN");

        http.exceptionHandling().accessDeniedPage("/accessDenied");
        http.authorizeRequests().anyRequest().authenticated();
        http.formLogin();
    }
}
