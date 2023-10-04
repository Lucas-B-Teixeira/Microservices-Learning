package mslearning.hroauth.service;

import lombok.RequiredArgsConstructor;
import mslearning.hroauth.feignClient.UserFeignClients;
import mslearning.hroauth.model.UserModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserFeignClients userFeignClients;

    public UserModel userFindByEmail(String email){
        UserModel userModel = userFeignClients.findByEmail(email).getBody();
        if(Objects.isNull(userModel)){
            throw new IllegalArgumentException("User Not Found");
        }
        return userModel;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userFeignClients.findByEmail(username).getBody();
        if(Objects.isNull(userModel)){
            throw new UsernameNotFoundException("User Not Found");
        }
        return userModel;
    }
}
