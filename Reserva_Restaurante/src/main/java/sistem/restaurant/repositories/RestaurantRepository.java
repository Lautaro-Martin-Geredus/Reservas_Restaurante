package sistem.restaurant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistem.restaurant.entities.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>
{

}
