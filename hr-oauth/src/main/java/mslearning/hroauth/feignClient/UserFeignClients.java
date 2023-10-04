package mslearning.hroauth.feignClient;

import mslearning.hroauth.config.AppConfig;
import mslearning.hroauth.model.UserModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "hr-user", path = "/users", configuration = AppConfig.class)
public interface UserFeignClients {

    @GetMapping(value = "/search/{username}")
    public ResponseEntity<UserModel> findByEmail(@PathVariable String username);

}
