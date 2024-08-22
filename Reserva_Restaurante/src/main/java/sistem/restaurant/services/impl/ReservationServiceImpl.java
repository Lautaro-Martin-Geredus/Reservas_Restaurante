package sistem.restaurant.services.impl;

import jakarta.persistence.EntityExistsException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sistem.restaurant.dtos.menuItem.NewMenuItemDto;
import sistem.restaurant.dtos.reservation.NewReservationDto;
import sistem.restaurant.dtos.reservation.ReservationDto;
import sistem.restaurant.entities.MenuItem;
import sistem.restaurant.entities.Reservation;
import sistem.restaurant.entities.Tablee;
import sistem.restaurant.entities.User;
import sistem.restaurant.repositories.ReservationRepository;
import sistem.restaurant.repositories.TableeRepository;
import sistem.restaurant.repositories.UserRepository;
import sistem.restaurant.services.ReservationService;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService
{
    @Autowired private ReservationRepository reservationRepository;
    @Autowired private TableeRepository tableeRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private ModelMapper modelMapper;

    @Override
    public ReservationDto createReservation(String userName, int tableNumber,
                                            NewReservationDto newReservationDto)
    {
        Optional<User> userOptional = userRepository.findByName(userName);
        Optional<Tablee> tableOptional = tableeRepository.findByNumber(tableNumber);
        Optional<Reservation> reservationOptional = reservationRepository.findByClientName(newReservationDto.getClientName());

        if(userOptional.isPresent() && tableOptional.isPresent() && reservationOptional.isPresent())
        {
            throw new EntityExistsException("Reservation already exist!");
        }
        Reservation reservation = new Reservation();
        reservation.setUser(modelMapper.map(userOptional.get(), User.class));
        reservation.setClientName(newReservationDto.getClientName());
        reservation.setTable(modelMapper.map(tableOptional.get(), Tablee.class));
        reservation.setReservationDateTime(newReservationDto.getReservationDateTime());
        reservationRepository.save(reservation);

        ReservationDto r = modelMapper.map(reservation, ReservationDto.class);
        r.setUserName(userOptional.get().getName());
        r.setTableNumber(tableOptional.get().getNumber());

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
    public List<NewReservationDto> getAllReservations()
    {
        List<Reservation> reservations = reservationRepository.findAll();
        Type listType = new TypeToken<List<NewReservationDto>>() {}.getType();

        return modelMapper.map(reservations, listType);
    }
}
