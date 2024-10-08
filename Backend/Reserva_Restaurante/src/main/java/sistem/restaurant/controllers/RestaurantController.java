package sistem.restaurant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistem.restaurant.dtos.restaurant.RestaurantDto;
import sistem.restaurant.services.RestaurantService;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController
{
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("CreateRestaurant")
    public ResponseEntity<RestaurantDto> createRestaurant(@RequestBody RestaurantDto restaurantDto)
    {
        return ResponseEntity.ok(restaurantService.createRestaurant(restaurantDto));
    }

    @PutMapping("updateRestaurant/{name}")
    public ResponseEntity<RestaurantDto> updateRestaurant(@PathVariable String name, @RequestBody RestaurantDto restaurantDto)
    {
        return ResponseEntity.ok(restaurantService.updateRestaurant(name, restaurantDto));
    }

    @DeleteMapping("DeleteRestaurat/{name}")
    public ResponseEntity<Boolean> deleteRestaurant(@PathVariable String name)
    {
        return ResponseEntity.ok(restaurantService.deleteRestaurant(name));
    }

    @GetMapping("GetRestaurant")
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants()
    {
        return ResponseEntity.ok(restaurantService.getAllRestaurant());
    }
}
