package sistem.restaurant.dtos.table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class TableeDto
{
    private int seats;
    private boolean available;
    private Long restaurantId;
}
