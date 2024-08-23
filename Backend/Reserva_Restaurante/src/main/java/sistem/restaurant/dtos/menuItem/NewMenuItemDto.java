package sistem.restaurant.dtos.menuItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class NewMenuItemDto
{
    private String name;
    private String description;
    private double price;
}

