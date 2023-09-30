package mslearning.hroauth.controller;

import lombok.RequiredArgsConstructor;
import mslearning.hroauth.model.UserModel;
import mslearning.hroauth.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/search")
    public ResponseEntity<UserModel> findByEmail(@RequestParam String email){
        try{
            UserModel userModel = userService.userFindByEmail(email);
            return ResponseEntity.ok(userModel);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
;        }
    }


}
