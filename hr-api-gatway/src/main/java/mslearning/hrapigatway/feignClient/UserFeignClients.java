package mslearning.hrapigatway.feignClient;

import mslearning.hrapigatway.model.UserModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;

@FeignClient(name = "hr-user", path = "/users")
public interface UserFeignClients {
    @GetMapping(value = "/search")
    Mono<UserModel> findByEmail(@RequestParam String email);
}
