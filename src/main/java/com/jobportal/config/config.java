package com.jobportal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.jobportal.service.MyuserService;

@Configuration
@EnableWebSecurity
public class config {

    @Autowired
    private MyuserService myuserService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable());

        http.authorizeHttpRequests(auth -> auth
                // public URLs
                .requestMatchers("/login","/", "/contactus", "/images/**",
                                 "/registerForm", "/register1Data", "/register2Data","/contactus","/AdminRegisterForm","/adminregisterData")
                .permitAll()

                // USER URLs
                .requestMatchers("/user/**").hasRole("USER")

                // ADMIN URLs
                .requestMatchers("/admin/**").hasRole("ADMIN")

                // everything else needs login
                .anyRequest().authenticated()
        );

        // USER login
        http.formLogin(form -> form
                .loginPage("/login")                   // common login page
                .loginProcessingUrl("/loginValidate")
                .successHandler((request, response, authentication) -> {

                    // Redirect based on role
                    String role = authentication.getAuthorities().iterator().next().getAuthority();

                    if (role.equals("ROLE_ADMIN")) {
                        response.sendRedirect("/Admin/getall");
                    } else {
                        response.sendRedirect("/User/loginSuccess");
                    }
                })
                .permitAll()
        );

        // USER logout
        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
        );
        http.userDetailsService(myuserService);

        return http.build();
    }
}
