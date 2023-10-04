package mslearning.hrapigatway.config;

import lombok.RequiredArgsConstructor;
import mslearning.hrapigatway.security.GatewayTokenFilter;
import mslearning.hrapigatway.service.JwtUtil;
import mslearning.hrapigatway.service.UserDetailsSecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;


import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsSecurityService userDetailsSecurityService;
    private final JwtUtil jwtUtil;

    private static final String[] PUBLIC_MATCHERS_POST = {
            "/hr-oauth/login"
    };

    private static final String[] OPERATOR = {
            "/hr-worker/**"
    };

    private static final String[] ADMIN = {
            "/hr-payroll/**"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ReactiveAuthenticationManager authenticationManager() {
        UserDetailsRepositoryReactiveAuthenticationManager authenticationManager =
                new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsSecurityService);
        authenticationManager.setPasswordEncoder(passwordEncoder());
        return authenticationManager;
    }


    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) throws Exception{

        return http.csrf(csrf -> csrf.disable())
            .authorizeExchange(a -> a
                .pathMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
                .pathMatchers(HttpMethod.GET, OPERATOR).hasAnyRole("OPERATOR", "ADMIN")
                .pathMatchers(HttpMethod.GET, ADMIN).hasAnyRole("ADMIN")
                .anyExchange().authenticated())
            .authenticationManager(authenticationManager())
            .httpBasic(httpBasicSpec -> httpBasicSpec.disable())
            .formLogin(formLoginSpec -> formLoginSpec.disable())
            .addFilterAt(new GatewayTokenFilter(jwtUtil, userDetailsSecurityService), SecurityWebFiltersOrder.AUTHENTICATION)
            .build();
    }

}
