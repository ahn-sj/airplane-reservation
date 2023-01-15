package airplainreservation.highestway.common.config;

import airplainreservation.highestway.common.security.TokenAuthenticationFilter;
import airplainreservation.highestway.common.security.RestAuthenticationEntryPoint;
import airplainreservation.highestway.common.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenProvider tokenProvider;

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().disable()
                .and()
                .cors()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable();

        http
                .exceptionHandling().authenticationEntryPoint(new RestAuthenticationEntryPoint());

        http
                .authorizeRequests()
                .antMatchers("/","/registry","/login", "/css/**").permitAll()
                .antMatchers("/member/**").authenticated() // 일반사용자 접근 가능
                .antMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN") // 매니저, 관리자 접근 가능
                .antMatchers("/admin/**").hasRole("ADMIN"); // 관리자만 접근 가능

        http
                .addFilterBefore((tokenAuthenticationFilter()), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
