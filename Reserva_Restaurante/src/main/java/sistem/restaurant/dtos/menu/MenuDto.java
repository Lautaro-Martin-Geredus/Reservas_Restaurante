package sistem.restaurant.dtos.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sistem.restaurant.dtos.restaurant.RestaurantDto;

@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class MenuDto
{
    private String category;
    private RestaurantDto restaurantName;
}
