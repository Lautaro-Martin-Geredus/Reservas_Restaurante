package sistem.restaurant.dtos.table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sistem.restaurant.dtos.restaurant.RestaurantDto;

@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class NewTableDto
{
    private int number;
    private int seats;
    private boolean available;
}