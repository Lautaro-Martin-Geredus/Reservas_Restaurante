package sistem.restaurant.dtos.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class MenuItemDto
{
    private String name;
    private String description;
    private double price;
    private Long MenuId;
}
