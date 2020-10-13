package attendanceservice.attendanceservicetesttask.security;

import attendanceservice.attendanceservicetesttask.domain.User;
import attendanceservice.attendanceservicetesttask.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BasicAuthenticationProvider implements AuthenticationProvider {
    private UserService userService;

    public BasicAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        try{
            User user = userService.getOneByUsername(username);
            if(!user.passwordIsEqual(password)) {
                throw new BadCredentialsException("Bad password");
            }
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
            return new UsernamePasswordAuthenticationToken(user.getUsername(), password, authorities);
        }
        catch (EntityNotFoundException ex){
            throw new UsernameNotFoundException(ex.getMessage(), ex);
        }
        catch (IllegalArgumentException ex){
            throw new UsernameNotFoundException(ex.getMessage(), ex);
        }
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}
