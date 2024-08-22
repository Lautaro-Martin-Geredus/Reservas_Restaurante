package sistem.restaurant.services.impl;

import jakarta.persistence.EntityExistsException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistem.restaurant.dtos.restaurant.RestaurantDto;
import sistem.restaurant.dtos.table.NewTableDto;
import sistem.restaurant.entities.Restaurant;
import sistem.restaurant.repositories.RestaurantRepository;
import sistem.restaurant.services.RestaurantService;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService
{
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RestaurantDto createRestaurant(RestaurantDto restaurantDto)
    {
        Optional<Restaurant> restaurant = restaurantRepository.findByName(restaurantDto.getName());
        if(restaurant.isPresent())
        {
            throw new EntityExistsException("This restaurant is already registered!");
        }
        Restaurant restaurant1 = new Restaurant();
        restaurant1.setName(restaurantDto.getName());
        restaurant1.setAddress(restaurantDto.getAddress());
        restaurant1.setPhoneNumber(restaurantDto.getPhoneNumber());
        restaurant1.setOpeningHours(restaurantDto.getOpeningHours());

        restaurantRepository.save(restaurant1);
        return modelMapper.map(restaurant1, RestaurantDto.class);
    }

    @Override
    public RestaurantDto updateRestaurant(String name, RestaurantDto restaurantDto)
    {
        Optional<Restaurant> restaurant = restaurantRepository.findByName(name);
        if(restaurant.isEmpty())
        {
            throw new EntityExistsException("Not restaurant found!");
        }
        Restaurant restaurant1 = restaurant.get();
        restaurant1.setName(restaurantDto.getName());
        restaurant1.setAddress(restaurantDto.getAddress());
        restaurant1.setPhoneNumber(restaurantDto.getPhoneNumber());
        restaurant1.setOpeningHours(restaurantDto.getOpeningHours());

        restaurantRepository.save(restaurant1);
        return modelMapper.map(restaurant1, RestaurantDto.class);
    }

    @Override
    public boolean deleteRestaurant(String name)
    {
        Optional<Restaurant> restaurant = restaurantRepository.findByName(name);
        if(restaurant.isPresent())
        {
            Restaurant restaurant1 = restaurant.get();
            restaurantRepository.delete(restaurant1);

            return true;
        }
        return false;
    }

    @Override
    public List<RestaurantDto> getAllRestaurant()
    {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        Type listType = new TypeToken<List<RestaurantDto>>() {}.getType();

        return modelMapper.map(restaurants, listType);
    }
}
