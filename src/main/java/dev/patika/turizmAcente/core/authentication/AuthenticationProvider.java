package dev.patika.turizmAcente.core.authentication;

import dev.patika.turizmAcente.business.concretes.UserManager;
import dev.patika.turizmAcente.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class AuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider {
    private final UserManager userManager;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());
        Users userLogin = this.userManager.findByNameAndPassword(username, password);
        if (userLogin != null) {
            if(userLogin.getRole().equals(Users.Role.ADMIN)){
                System.out.println("ADMİN GİRDİİİ");
                return new UsernamePasswordAuthenticationToken(username, password, Arrays.asList());
            } else {
                System.out.println("EMPLOYEEE GİRDİİİ");
                return new UsernamePasswordAuthenticationToken(username, password, Arrays.asList());
            }
        } else {
            throw new AuthenticationCredentialsNotFoundException("Error!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
