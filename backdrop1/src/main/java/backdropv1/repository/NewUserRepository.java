package backdropv1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import backdropv1.model.NewUser;

@Repository
public interface NewUserRepository extends JpaRepository<NewUser, String>{

}
