package sistem.restaurant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistem.restaurant.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>
{

}
