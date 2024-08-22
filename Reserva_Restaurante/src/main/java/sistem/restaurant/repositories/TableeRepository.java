package sistem.restaurant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistem.restaurant.entities.Tablee;

import java.util.Optional;

@Repository
public interface TableeRepository extends JpaRepository<Tablee, Long>
{
    Optional<Tablee> findByNumber(int number);
}
