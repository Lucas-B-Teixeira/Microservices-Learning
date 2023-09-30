package mslearning.hroauth.model;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserModel {

    private Long id;

    private String name;

    private String email;

    private String password;

    @Builder.Default
    Set<RoleModel> roles = new HashSet<>();

}
