package mslearning.hruser.repository;

import mslearning.hruser.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserModel findByEmail(String email);

}
