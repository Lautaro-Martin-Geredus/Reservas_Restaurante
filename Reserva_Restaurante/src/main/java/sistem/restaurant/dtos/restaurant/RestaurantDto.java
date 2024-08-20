package sistem.restaurant.dtos.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class RestaurantDto
{
    private String name;
    private String address;
    private String phoneNumber;
    private String openingHours;
}
