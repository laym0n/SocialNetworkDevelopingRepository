package social.network.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import social.network.dto.UserAppDTO;
import social.network.entities.user.User;
import social.network.security.UserSecurityService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends GlobalAuthenticationConfigurerAdapter {
    @Autowired
    private UserSecurityService userService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, ResourceLoader resourceLoader) throws Exception {
        UserAppDTO.resourceLoader = resourceLoader;
        return http
                .authorizeRequests()
                .requestMatchers("/registration").permitAll()
                .requestMatchers("/administrate").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin(form->{
                    form
                            .loginPage("/login")
                            .defaultSuccessUrl("/chats", true)
                            .permitAll();
                })
                .logout(logout -> {
                    logout
                            .deleteCookies("JSESSIONID")
                            .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                            .logoutSuccessUrl("/login?logout=true")
                            .invalidateHttpSession(true);
                })
                .rememberMe(rememberMe ->
                        rememberMe.key("uniqueAndSecret")
                )
                .build();

    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }
}
