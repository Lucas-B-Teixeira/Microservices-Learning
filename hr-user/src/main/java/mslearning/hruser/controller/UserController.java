package mslearning.hruser.controller;

import lombok.RequiredArgsConstructor;
import mslearning.hruser.model.UserModel;
import mslearning.hruser.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserModel> findById(@PathVariable Long id){
        UserModel userModel = userRepository.findById(id).get();
        return ResponseEntity.ok(userModel);
    }

    @GetMapping(value = "/search/{username}")
    public ResponseEntity<UserModel> findByEmail(@PathVariable String username){
        UserModel userModel = userRepository.findByEmail(username);
        return ResponseEntity.ok(userModel);
    }

}
