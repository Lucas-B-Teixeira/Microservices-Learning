package mslearning.hrapigatway.service;

import lombok.RequiredArgsConstructor;
import mslearning.hrapigatway.model.UserDetailsSecurityModel;
import mslearning.hrapigatway.model.UserModel;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserDetailsSecurityService implements ReactiveUserDetailsService {

    private final WebClient.Builder webClientBuilder;

    public UserDetails userModelToUserDetails(UserModel userModel){
        return UserDetailsSecurityModel.builder()
                .id(userModel.getId())
                .userName(userModel.getUsername())
                .roles(userModel.getRoles())
                .password(userModel.getPassword())
                .build();
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {

        return webClientBuilder
                .baseUrl("lb://hr-user")
                .build()
                .get()
                .uri("/users/search/" + username)
                .retrieve()
                .bodyToMono(UserModel.class)
                .flatMap(userModel -> {
                    UserDetails userDetails = userModelToUserDetails(userModel);
                    return Mono.just(userDetails);
                })
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("User Not Found")));

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
