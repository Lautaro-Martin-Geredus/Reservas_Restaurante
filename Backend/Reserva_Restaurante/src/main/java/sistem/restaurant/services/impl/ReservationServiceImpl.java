package sistem.restaurant.services.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sistem.restaurant.dtos.menuItem.NewMenuItemDto;
import sistem.restaurant.dtos.reservation.NewReservationDto;
import sistem.restaurant.dtos.reservation.ReservationDto;
import sistem.restaurant.entities.*;
import sistem.restaurant.repositories.ReservationRepository;
import sistem.restaurant.repositories.RestaurantRepository;
import sistem.restaurant.repositories.TableeRepository;
import sistem.restaurant.repositories.UserRepository;
import sistem.restaurant.services.ReservationService;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService
{
    @Autowired private ReservationRepository reservationRepository;
    @Autowired private TableeRepository tableeRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private ModelMapper modelMapper;
    @Autowired private RestaurantRepository restaurantRepository;

    @Override
    public ReservationDto createReservation(String userName, int tableNumber, String restaurantName,
                                            NewReservationDto newReservationDto)
    {
        Optional<User> userOptional = userRepository.findByName(userName);
        Optional<Tablee> tableOptional = tableeRepository.findByNumber(tableNumber);
        Optional<Restaurant> restaurantOptional = restaurantRepository.findByName(restaurantName);
        Optional<Reservation> reservationOptional = reservationRepository.findByClientName(newReservationDto.getClientName());

        if(userOptional.isEmpty() && tableOptional.isEmpty() && restaurantOptional.isEmpty())
        {
            throw new EntityNotFoundException("User, Table and Restaurant not found!");
        }

        if(reservationOptional.isPresent())
        {
            throw new EntityExistsException("Reservation already exists!");
        }

        Tablee table = tableOptional.get();

        if (!table.isAvailable())
        {
            throw new IllegalStateException("Table is already reserved!");
        }

        table.setAvailable(false);
        tableeRepository.save(table);

        Reservation reservation = new Reservation();
        reservation.setUser(userOptional.get());
        reservation.setClientName(newReservationDto.getClientName());
        reservation.setTable(table);
        reservation.setRestaurant(restaurantOptional.get());
        reservation.setReservationDateTime(newReservationDto.getReservationDateTime());

        reservationRepository.save(reservation);

        ReservationDto r = modelMapper.map(reservation, ReservationDto.class);
        r.setUserName(userOptional.get().getName());
        r.setTableNumber(table.getNumber());
        r.setRestaurantName(restaurantOptional.get().getName());

        return r;
    }

    @Override
    public boolean deleteReservation(String clientName)
    {
        Optional<Reservation> reservationOptional = reservationRepository.findByClientName(clientName);
        if(reservationOptional.isPresent())
        {
            reservationRepository.delete(reservationOptional.get());
            return true;
        }
        return false;
    }

    // Admin

    @Override
    public List<ReservationDto> getAllReservations()
    {
        List<Reservation> reservations = reservationRepository.findAll();
        Type listType = new TypeToken<List<ReservationDto>>() {}.getType();

        return modelMapper.map(reservations, listType);
    }
}
