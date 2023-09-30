package mslearning.hroauth.service;

import lombok.RequiredArgsConstructor;
import mslearning.hroauth.feignClient.UserFeignClients;
import mslearning.hroauth.model.UserModel;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserFeignClients userFeignClients;

    public UserModel userFindByEmail(String email){
        UserModel userModel = userFeignClients.findByEmail(email).getBody();
        if(Objects.isNull(userModel)){
            throw new IllegalArgumentException("User Not Found");
        }
        return userModel;
    }

}
