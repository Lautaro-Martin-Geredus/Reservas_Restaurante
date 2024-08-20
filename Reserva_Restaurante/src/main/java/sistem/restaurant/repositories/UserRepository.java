package sistem.restaurant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sistem.restaurant.entities.User;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    @Query("SELECT u FROM User u WHERE (u.name LIKE :identity OR u.email LIKE :identity) AND u.password LIKE :password")
    Optional<User> findByUserNameOrEmailAndPassword(@Param("identity") String identity, @Param("password") String password);

    Optional<User> findByEmail(String email);

    Optional<User> findByName(String name);
}
