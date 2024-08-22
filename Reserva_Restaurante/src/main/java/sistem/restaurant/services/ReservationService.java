package sistem.restaurant.services;

import org.springframework.stereotype.Service;
import sistem.restaurant.dtos.reservation.NewReservationDto;
import sistem.restaurant.dtos.reservation.ReservationDto;

import java.util.List;

@Service
public interface ReservationService
{
    ReservationDto createReservation(String userName, int tableNumber, NewReservationDto newReservationDto);

    boolean deleteReservation(String clientName);

    // Admin

    List<ReservationDto> getAllReservations();
}
