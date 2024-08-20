package sistem.restaurant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistem.restaurant.entities.MenuItem;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long>
{
    
}
