package sistem.restaurant.services.impl;

import jakarta.persistence.EntityExistsException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class TableServiceImpl implements TableService
{
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TableeRepository tableeRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public TableeDto createTable(String restaurantName, NewTableDto tableeDto)
    {
        Optional<Restaurant> restaurant = restaurantRepository.findByName(restaurantName);
        Optional<Tablee> tableeOptional = tableeRepository.findByNumber(tableeDto.getNumber());
        if(restaurant.isEmpty())
        {
            throw new EntityExistsException("Restaurant not found with name!");
        }
        if(tableeOptional.isPresent())
        {
            throw new EntityExistsException("Table with that number already exist!");
        }

        Tablee tablee = new Tablee();
        tablee.setNumber(tableeDto.getNumber());
        tablee.setSeats(tableeDto.getSeats());
        tablee.setAvailable(true);
        tablee.setRestaurant(modelMapper.map(restaurant.get(), Restaurant.class));

        tableeRepository.save(tablee);

        TableeDto t = modelMapper.map(tablee, TableeDto.class);
        t.setRestaurantName(restaurant.get().getName());
        return t;
    }

    @Override
    public List<TableeDto> getAllTables()
    {
        List<Tablee> table = tableeRepository.findAll();
        Type listType = new TypeToken<List<TableeDto>>() {}.getType();

        return modelMapper.map(table, listType);
    }
}

