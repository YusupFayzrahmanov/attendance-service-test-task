package attendanceservice.attendanceservicetesttask.config;

import attendanceservice.attendanceservicetesttask.security.BasicAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String LOGIN_ENDPOINT = "/api/v1/auth/**";
    private BasicAuthenticationProvider authenticationProvider;

    public SecurityConfig(BasicAuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests().antMatchers(SwaggerConfig.AUTH_WHITELIST).permitAll()
                .antMatchers(LOGIN_ENDPOINT).permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable().headers().frameOptions().disable();
    }
}
