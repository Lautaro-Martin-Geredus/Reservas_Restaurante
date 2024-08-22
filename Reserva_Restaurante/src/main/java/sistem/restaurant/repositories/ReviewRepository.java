package sistem.restaurant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistem.restaurant.entities.Review;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>
{
    Optional<Review> findByClientName(String clientName);
}
