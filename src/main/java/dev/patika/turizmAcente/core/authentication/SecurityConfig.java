package dev.patika.turizmAcente.core.authentication;

import dev.patika.turizmAcente.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers("/v1/admins/**").hasRole("ADMIN");
                    registry.requestMatchers("/v1/hotels/**").hasAnyRole("ADMIN","EMPLOYEE");
                    registry.requestMatchers("/v1/pensions/**").hasRole("EMPLOYEE");
                    registry.requestMatchers("/v1/rooms/**").hasRole("EMPLOYEE");
                    registry.requestMatchers("/v1/sessions/**").hasRole("EMPLOYEE");
                    registry.anyRequest().authenticated();
                })
                .formLogin(AbstractAuthenticationFilterConfigurer:: permitAll)
                //.httpBasic(Customizer.withDefaults())
                .build();
    }
    @Bean
    public UserDetailsService userDetailsService(){
        return customUserDetailsService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /*

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        *//*http.httpBasic(Customizer.withDefaults());
        http.authenticationProvider(authenticationProvider);
        //http.authorizeHttpRequests(c -> c.anyRequest().authenticated());
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/hello/**").hasAuthority("ADMIN")
                        .requestMatchers("/v1/admins/**").permitAll()
                        .requestMatchers("/v1/pensions/**").permitAll()
                        .anyRequest().authenticated()
                );*//*
        return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        var user = User
                .withUsername("bb")
                .password("bb")
                .authorities("read")
                .build();
        var user1 = User
                .withUsername("aa")
                .password("aa")
                .authorities("read", "create")
                .build();

        return new InMemoryUserDetailsManager(user1);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }*/

}
