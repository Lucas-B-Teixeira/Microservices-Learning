package mslearning.hrapigatway.security;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import mslearning.hrapigatway.exception.unauthorized.UnauthorizedException;
import mslearning.hrapigatway.service.JwtUtil;
import mslearning.hrapigatway.service.UserDetailsSecurityService;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Configuration
public class GatewayTokenFilter implements WebFilter {

    private JwtUtil jwtUtil;
    private UserDetailsSecurityService userDetailsSecurityService;

    public GatewayTokenFilter(JwtUtil token, UserDetailsSecurityService service) {
        this.jwtUtil = token;
        this.userDetailsSecurityService = service;
    }


    private Mono<UsernamePasswordAuthenticationToken> getAuthentication(String token){
        if(this.jwtUtil.isValidToken(token)){
            String userName = this.jwtUtil.getUserName(token);

            return this.userDetailsSecurityService.findByUsername(userName)
                    .flatMap(userDetails -> {
                        UsernamePasswordAuthenticationToken authenticatedUser = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        return Mono.just(authenticatedUser);
                    })
                    .switchIfEmpty(Mono.error(new UnauthorizedException("User Not Found!")));
        }
        return Mono.error(new UnauthorizedException("Token Invalid!"));
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        HttpHeaders headers = exchange.getRequest().getHeaders();
        List<String> listHeaders = headers.get(HttpHeaders.AUTHORIZATION);

        String token = null;

        if (Objects.nonNull(listHeaders)) {

            for (String h : listHeaders) {

                if (h != null && h.startsWith("Bearer ")) {
                    token = h.substring(7);
                    break;
                }
            }
        }
        
        if(Objects.nonNull(token)){
            return getAuthentication(token)
                    .flatMap(auth -> {
                        if (Objects.nonNull(auth)) {
                            return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(auth));
                        }
                        return Mono.error(new UnauthorizedException("Token Invalid!"));
                    });

        }
        return chain.filter(exchange);
    }


}