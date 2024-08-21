package sistem.restaurant.services.impl;

import jakarta.persistence.EntityExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistem.restaurant.dtos.table.NewTableDto;
import sistem.restaurant.dtos.table.TableeDto;
import sistem.restaurant.entities.Restaurant;
import sistem.restaurant.entities.Tablee;
import sistem.restaurant.repositories.RestaurantRepository;
import sistem.restaurant.repositories.TableeRepository;
import sistem.restaurant.services.RestaurantService;
import sistem.restaurant.services.TableService;

import java.util.Optional;

@Service
public class TableServiceImpl implements TableService
{
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TableeRepository tableeRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public TableeDto createTable(String restaurantName, NewTableDto tableeDto)
    {
        Optional<Restaurant> restaurant = restaurantRepository.findByName(restaurantName);
        if(restaurant.isEmpty())
        {
            throw new EntityExistsException("Restaurant not found with name!");
        }
        Tablee tablee = new Tablee();
        tablee.setSeats(tableeDto.getSeats());
        tablee.setAvailable(true);
        tablee.setRestaurant(modelMapper.map(restaurant.get(), Restaurant.class));

        tableeRepository.save(tablee);
        return modelMapper.map(tablee, TableeDto.class);
    }

    @Override
    public TableeDto updateTable(String restaurantName, NewTableDto tableeDto)
    {
        Optional<Restaurant> restaurant = restaurantRepository.findByName(restaurantName);
        if(restaurant.isPresent())
        {

            Tablee tablee = new Tablee();
            tablee.setSeats(tableeDto.getSeats());
            tablee.setAvailable(true);
            tablee.setRestaurant(modelMapper.map(restaurant.get(), Restaurant.class));

            tableeRepository.save(tablee);
            return modelMapper.map(tablee, TableeDto.class);
        }
        throw new EntityExistsException("Restaurant not found with name!");
    }

    @Override
    public boolean deleteTable(Long id)
    {
        tableeRepository
    }
}

