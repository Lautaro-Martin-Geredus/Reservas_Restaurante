package sistem.restaurant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistem.restaurant.dtos.reservation.NewReservationDto;
import sistem.restaurant.dtos.reservation.ReservationDto;
import sistem.restaurant.services.ReservationService;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController
{
    @Autowired
    private ReservationService reservationService;

    @PostMapping("/{userName}/{tableNumber}/{restaurantName}")
    public ResponseEntity<ReservationDto> createReservation(@PathVariable String userName, @PathVariable int tableNumber,
                                                            @PathVariable String restaurantName,
                                                            @RequestBody NewReservationDto newReservationDto)
    {
        return ResponseEntity.ok(reservationService.createReservation(userName, tableNumber, restaurantName, newReservationDto));
    }

    @DeleteMapping("/{clientName}")
    public ResponseEntity<Boolean> deleteReservation(@PathVariable String clientName)
    {
        return ResponseEntity.ok(reservationService.deleteReservation(clientName));
    }

    @GetMapping("")
    public ResponseEntity<List<ReservationDto>> getAllReservations()
    {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }
}
