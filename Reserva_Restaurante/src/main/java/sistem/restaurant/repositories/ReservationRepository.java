package sistem.restaurant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistem.restaurant.entities.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>
{

}
