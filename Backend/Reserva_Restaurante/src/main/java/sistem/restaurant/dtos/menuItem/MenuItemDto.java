package sistem.restaurant.dtos.menuItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sistem.restaurant.dtos.menu.MenuDto;

@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class MenuItemDto
{
    private String name;
    private String description;
    private double price;
    //private String MenuName;
}
