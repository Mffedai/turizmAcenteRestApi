/*
package dev.patika.turizmAcente.core.authentication;

import dev.patika.turizmAcente.core.authentication.AuthenticationProvider;
import dev.patika.turizmAcente.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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

    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());
        http.authenticationProvider(authenticationProvider);
        //http.authorizeHttpRequests(c -> c.anyRequest().authenticated());
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/hello/**").hasAuthority("ADMIN")
                        .requestMatchers("/v1/admins/**").permitAll()
                        .requestMatchers("/v1/pensions/**").permitAll()
                        .anyRequest().authenticated()
                );
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
    }

}
*/
