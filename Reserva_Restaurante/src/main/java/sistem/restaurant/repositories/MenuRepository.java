package sistem.restaurant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistem.restaurant.entities.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long>
{

}
