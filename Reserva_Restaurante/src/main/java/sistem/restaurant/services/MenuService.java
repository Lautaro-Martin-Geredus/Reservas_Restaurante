package sistem.restaurant.services;

import org.springframework.stereotype.Service;
import sistem.restaurant.dtos.menu.MenuDto;
import sistem.restaurant.dtos.menu.MenuItemDto;
import sistem.restaurant.dtos.menu.NewMenuDto;

@Service
public interface MenuService
{
    MenuDto createMenu(String restaurantName, NewMenuDto newMenuDto);

    MenuDto updateMenu(String menuCategory, String restaurantName, NewMenuDto menuDto);

    boolean deleteMenu(String menuCategory);

    // Menu Item

    MenuItemDto createMenuItem(MenuItemDto menuItemDto);

    MenuItemDto updateMenuItem(MenuItemDto menuItemDto);

    boolean deleteMenuItem(String menuName);
}
