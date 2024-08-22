package sistem.restaurant.services;

import org.springframework.stereotype.Service;
import sistem.restaurant.dtos.restaurant.RestaurantDto;

import java.util.List;

@Service
public interface RestaurantService
{
    RestaurantDto createRestaurant(RestaurantDto restaurantDto);

    RestaurantDto updateRestaurant(String name, RestaurantDto restaurantDto);

    boolean deleteRestaurant(String name);

    List<RestaurantDto> getAllRestaurant();
}

